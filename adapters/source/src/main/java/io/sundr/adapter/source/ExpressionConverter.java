package io.sundr.adapter.source;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.body.VariableDeclaratorId;
import com.github.javaparser.ast.expr.ArrayAccessExpr;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.DoubleLiteralExpr;
import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.InstanceOfExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.expr.LongLiteralExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;

import io.sundr.adapter.api.AdapterContext;
import io.sundr.model.Assign;
import io.sundr.model.BitwiseAnd;
import io.sundr.model.BitwiseOr;
import io.sundr.model.ClassRef;
import io.sundr.model.Construct;
import io.sundr.model.Declare;
import io.sundr.model.Divide;
import io.sundr.model.Enclosed;
import io.sundr.model.Equals;
import io.sundr.model.GreaterThan;
import io.sundr.model.GreaterThanOrEqual;
import io.sundr.model.Index;
import io.sundr.model.InstanceOf;
import io.sundr.model.Inverse;
import io.sundr.model.Lambda;
import io.sundr.model.LeftShift;
import io.sundr.model.LessThan;
import io.sundr.model.LessThanOrEqual;
import io.sundr.model.LogicalAnd;
import io.sundr.model.LogicalOr;
import io.sundr.model.Minus;
import io.sundr.model.Modulo;
import io.sundr.model.Multiply;
import io.sundr.model.Negative;
import io.sundr.model.Not;
import io.sundr.model.NotEquals;
import io.sundr.model.Plus;
import io.sundr.model.Positive;
import io.sundr.model.PostDecrement;
import io.sundr.model.PostIncrement;
import io.sundr.model.PreDecrement;
import io.sundr.model.PreIncrement;
import io.sundr.model.Property;
import io.sundr.model.PropertyRef;
import io.sundr.model.RightShift;
import io.sundr.model.RightUnsignedShift;
import io.sundr.model.This;
import io.sundr.model.TypeRef;
import io.sundr.model.ValueRef;
import io.sundr.model.Xor;

public class ExpressionConverter {

  private static final ClassRef NUMBER = ClassRef.forName(Number.class.getName());
  private static final ClassRef OBJECT = ClassRef.forName(Object.class.getName());
  private static final SourceAdapter SOURCE_ADAPTER = new SourceAdapter(AdapterContext.getContext());
  private static final TypeToTypeRef TYPEREF_ADAPTER = new TypeToTypeRef(SOURCE_ADAPTER.getReferenceAdapterFunction());

  public static Declare convertVarDeclaration(VariableDeclarationExpr expr) {
    TypeRef typeRef = TYPEREF_ADAPTER.apply(expr.getType());
    List<Property> properties = expr.getVars().stream()
        .map(VariableDeclarator::getId)
        .map(VariableDeclaratorId::getName)
        .map(n -> Property.newProperty(typeRef, n))
        .collect(Collectors.toList());
    return new Declare(properties, Optional.empty());
  }

  public static io.sundr.model.Expression convertExpression(Expression expression) {
    if (expression instanceof AssignExpr) {
      AssignExpr assignExpr = (AssignExpr) expression;
      return new Assign(convertExpression(assignExpr.getTarget()), convertExpression(assignExpr.getValue()));
    } else if (expression instanceof UnaryExpr) {
      UnaryExpr unaryExpr = (UnaryExpr) expression;
      switch (unaryExpr.getOperator()) {
        case positive:
          return new Positive(convertExpression(unaryExpr.getExpr()));
        case negative:
          return new Negative(convertExpression(unaryExpr.getExpr()));
        case preIncrement:
          return new PreIncrement(convertExpression(unaryExpr.getExpr()));
        case preDecrement:
          return new PreDecrement(convertExpression(unaryExpr.getExpr()));
        case posIncrement:
          return new PostIncrement(convertExpression(unaryExpr.getExpr()));
        case posDecrement:
          return new PostDecrement(convertExpression(unaryExpr.getExpr()));
        case not:
          return new Not(convertExpression(unaryExpr.getExpr()));
        case inverse:
          return new Inverse(convertExpression(unaryExpr.getExpr()));
      }
    } else if (expression instanceof BinaryExpr) {
      BinaryExpr binaryExpr = (BinaryExpr) expression;
      switch (binaryExpr.getOperator()) {
        case plus:
          return new Plus(convertExpression(binaryExpr.getLeft()), convertExpression(binaryExpr.getRight()));
        case minus:
          return new Minus(convertExpression(binaryExpr.getLeft()), convertExpression(binaryExpr.getRight()));
        case and:
          return new LogicalAnd(convertExpression(binaryExpr.getLeft()), convertExpression(binaryExpr.getRight()));
        case or:
          return new LogicalOr(convertExpression(binaryExpr.getLeft()), convertExpression(binaryExpr.getRight()));
        case binOr:
          return new BitwiseOr(convertExpression(binaryExpr.getLeft()), convertExpression(binaryExpr.getRight()));
        case binAnd:
          return new BitwiseAnd(convertExpression(binaryExpr.getLeft()), convertExpression(binaryExpr.getRight()));
        case xor:
          return new Xor(convertExpression(binaryExpr.getLeft()), convertExpression(binaryExpr.getRight()));
        case equals:
          return new Equals(convertExpression(binaryExpr.getLeft()), convertExpression(binaryExpr.getRight()));
        case notEquals:
          return new NotEquals(convertExpression(binaryExpr.getLeft()), convertExpression(binaryExpr.getRight()));
        case greater:
          return new GreaterThan(convertExpression(binaryExpr.getLeft()), convertExpression(binaryExpr.getRight()));
        case greaterEquals:
          return new GreaterThanOrEqual(convertExpression(binaryExpr.getLeft()), convertExpression(binaryExpr.getRight()));
        case less:
          return new LessThan(convertExpression(binaryExpr.getLeft()), convertExpression(binaryExpr.getRight()));
        case lessEquals:
          return new LessThanOrEqual(convertExpression(binaryExpr.getLeft()), convertExpression(binaryExpr.getRight()));
        case times:
          return new Multiply(convertExpression(binaryExpr.getLeft()), convertExpression(binaryExpr.getRight()));
        case divide:
          return new Divide(convertExpression(binaryExpr.getLeft()), convertExpression(binaryExpr.getRight()));
        case remainder:
          return new Modulo(convertExpression(binaryExpr.getLeft()), convertExpression(binaryExpr.getRight()));
        case rSignedShift:
          return new RightShift(convertExpression(binaryExpr.getLeft()), convertExpression(binaryExpr.getRight()));
        case rUnsignedShift:
          return new RightUnsignedShift(convertExpression(binaryExpr.getLeft()), convertExpression(binaryExpr.getRight()));
        case lShift:
          return new LeftShift(convertExpression(binaryExpr.getLeft()), convertExpression(binaryExpr.getRight()));
      }
    } else if (expression instanceof NameExpr) {
      NameExpr nameExpr = (NameExpr) expression;
      return new PropertyRef(Property.newProperty(OBJECT, nameExpr.getName()));
    } else if (expression instanceof StringLiteralExpr) {
      StringLiteralExpr stringLiteralExpr = (StringLiteralExpr) expression;
      return new ValueRef(stringLiteralExpr.getValue());
    } else if (expression instanceof IntegerLiteralExpr) {
      IntegerLiteralExpr integerLiteralExpr = (IntegerLiteralExpr) expression;
      return new ValueRef(integerLiteralExpr.getValue());
    } else if (expression instanceof LongLiteralExpr) {
      LongLiteralExpr longLiteralExpr = (LongLiteralExpr) expression;
      return new ValueRef(longLiteralExpr.getValue());
    } else if (expression instanceof DoubleLiteralExpr) {
      DoubleLiteralExpr doubleLiteralExpr = (DoubleLiteralExpr) expression;
      return new ValueRef(doubleLiteralExpr.getValue());
    } else if (expression instanceof BooleanLiteralExpr) {
      BooleanLiteralExpr booleanLiteralExpr = (BooleanLiteralExpr) expression;
      return new ValueRef(booleanLiteralExpr.getValue());
    } else if (expression instanceof ThisExpr) {
      return new This();
    } else if (expression instanceof ObjectCreationExpr) {
      ObjectCreationExpr objectCreationExpr = (ObjectCreationExpr) expression;
      ClassRef classRef = (ClassRef) SOURCE_ADAPTER.getReferenceAdapterFunction().apply(objectCreationExpr.getType());
      List<io.sundr.model.TypeRef> parameters = new ArrayList<>();
      List<io.sundr.model.Expression> arguments = new ArrayList<>();
      for (Type type : objectCreationExpr.getTypeArgs()) {
        if (type instanceof ClassOrInterfaceType) {
          parameters.add(SOURCE_ADAPTER.getReferenceAdapterFunction().apply((ClassOrInterfaceType) type));
        }
      }
      for (Expression argument : objectCreationExpr.getArgs()) {
        arguments.add(convertExpression(argument));
      }
      return new Construct(classRef, parameters, arguments);
    } else if (expression instanceof MethodCallExpr) {
      MethodCallExpr methodCallExpr = (MethodCallExpr) expression;
      String methodName = methodCallExpr.getName();
      List<io.sundr.model.TypeRef> parameters = new ArrayList<>();
      List<io.sundr.model.Expression> arguments = new ArrayList<>();
      for (Type type : methodCallExpr.getTypeArgs()) {
        if (type instanceof ClassOrInterfaceType) {
          parameters.add(SOURCE_ADAPTER.getReferenceAdapterFunction().apply((ClassOrInterfaceType) type));
        }
      }
      for (Expression argument : methodCallExpr.getArgs()) {
        arguments.add(convertExpression(argument));
      }
      methodCallExpr.getScope();
    } else if (expression instanceof LambdaExpr) {
      LambdaExpr lambdaExpr = (LambdaExpr) expression;
      return new Lambda(lambdaExpr.getParameters().stream().map(Parameter::getId).map(VariableDeclaratorId::getName)
          .collect(Collectors.toList()), StatementConverter.convertStatement(lambdaExpr.getBody()));
    } else if (expression instanceof EnclosedExpr) {
      EnclosedExpr enclosedExpr = (EnclosedExpr) expression;
      return new Enclosed(convertExpression(enclosedExpr.getInner()));
    } else if (expression instanceof InstanceOfExpr) {
      InstanceOfExpr instanceOfExpr = (InstanceOfExpr) expression;
      return new InstanceOf(convertExpression(instanceOfExpr.getExpr()),
          (ClassRef) SOURCE_ADAPTER.getReferenceAdapterFunction().apply((ClassOrInterfaceType) instanceOfExpr.getType()));
    } else if (expression instanceof ArrayAccessExpr) {
      ArrayAccessExpr arrayAccessExpr = (ArrayAccessExpr) expression;
      return new Index(convertExpression(arrayAccessExpr.getName()), convertExpression(arrayAccessExpr.getIndex()));
    }
    return null;
  }
}
