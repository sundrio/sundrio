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
import com.github.javaparser.ast.expr.CastExpr;
import com.github.javaparser.ast.expr.ClassExpr;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.expr.DoubleLiteralExpr;
import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.InstanceOfExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.expr.LongLiteralExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.MethodReferenceExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.expr.TypeExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;

import io.sundr.adapter.api.AdapterContext;
import io.sundr.model.Assign;
import io.sundr.model.BitwiseAnd;
import io.sundr.model.BitwiseOr;
import io.sundr.model.Cast;
import io.sundr.model.ClassRef;
import io.sundr.model.Construct;
import io.sundr.model.ContextRef;
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
import io.sundr.model.MethodCall;
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
import io.sundr.model.Return;
import io.sundr.model.RightShift;
import io.sundr.model.RightUnsignedShift;
import io.sundr.model.Ternary;
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
    // Create adapters following the same pattern as TypeDeclarationToTypeDef
    ClassOrInterfaceToTypeRef classOrInterfaceToTypeRef = new ClassOrInterfaceToTypeRef();
    TypeToTypeRef typeAdapter = new TypeToTypeRef(classOrInterfaceToTypeRef);
    TypeRef typeRef = typeAdapter.apply(expr.getType());

    List<Property> properties = expr.getVars().stream()
        .map(v -> v.getId().getName())
        .map(n -> Property.newProperty(typeRef, n))
        .collect(Collectors.toList());

    // Handle initialization if present
    Optional<io.sundr.model.Expression> initValue = Optional.empty();
    if (!expr.getVars().isEmpty() && expr.getVars().get(0).getInit() != null) {
      initValue = Optional.of(convertExpression(expr.getVars().get(0).getInit()));
    }

    return new Declare(properties, initValue);
  }

  public static Property convertParameter(Parameter parameter) {
    // Create adapters following the same pattern as TypeDeclarationToTypeDef
    ClassOrInterfaceToTypeRef classOrInterfaceToTypeRef = new ClassOrInterfaceToTypeRef();
    TypeToTypeRef typeAdapter = new TypeToTypeRef(classOrInterfaceToTypeRef);
    TypeRef typeRef = typeAdapter.apply(parameter.getType());

    return Property.newProperty(typeRef, parameter.getId().getName());
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
      io.sundr.model.Expression left = convertExpression(binaryExpr.getLeft());
      io.sundr.model.Expression right = convertExpression(binaryExpr.getRight());

      // Protect against null operands that would cause NPEs during rendering
      if (left == null || right == null) {
        return null;
      }

      switch (binaryExpr.getOperator()) {
        case plus:
          return new Plus(left, right);
        case minus:
          return new Minus(left, right);
        case and:
          return new LogicalAnd(left, right);
        case or:
          return new LogicalOr(left, right);
        case binOr:
          return new BitwiseOr(left, right);
        case binAnd:
          return new BitwiseAnd(left, right);
        case xor:
          return new Xor(left, right);
        case equals:
          return new Equals(left, right);
        case notEquals:
          return new NotEquals(left, right);
        case greater:
          return new GreaterThan(left, right);
        case greaterEquals:
          return new GreaterThanOrEqual(left, right);
        case less:
          return new LessThan(left, right);
        case lessEquals:
          return new LessThanOrEqual(left, right);
        case times:
          return new Multiply(left, right);
        case divide:
          return new Divide(left, right);
        case remainder:
          return new Modulo(left, right);
        case rSignedShift:
          return new RightShift(left, right);
        case rUnsignedShift:
          return new RightUnsignedShift(left, right);
        case lShift:
          return new LeftShift(left, right);
      }
      // If we get here, there's an unhandled binary operator
      return null;
    } else if (expression instanceof NameExpr) {
      NameExpr nameExpr = (NameExpr) expression;
      return new ContextRef(nameExpr.getName());
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
    } else if (expression instanceof NullLiteralExpr) {
      return new ValueRef(null);
    } else if (expression instanceof ThisExpr) {
      return new This();
    } else if (expression instanceof ObjectCreationExpr) {
      ObjectCreationExpr objectCreationExpr = (ObjectCreationExpr) expression;
      ClassRef classRef = (ClassRef) SOURCE_ADAPTER.getReferenceAdapterFunction().apply(objectCreationExpr.getType());
      List<TypeRef> parameters = new ArrayList<>();
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
      List<TypeRef> parameters = new ArrayList<>();
      List<io.sundr.model.Expression> arguments = new ArrayList<>();
      for (Type type : methodCallExpr.getTypeArgs()) {
        if (type instanceof ClassOrInterfaceType) {
          parameters.add(SOURCE_ADAPTER.getReferenceAdapterFunction().apply((ClassOrInterfaceType) type));
        }
      }
      for (Expression argument : methodCallExpr.getArgs()) {
        arguments.add(convertExpression(argument));
      }
      io.sundr.model.Expression scope = methodCallExpr.getScope() != null ? convertExpression(methodCallExpr.getScope()) : null;
      return new MethodCall(methodName, scope, parameters, arguments);
    } else if (expression instanceof LambdaExpr) {
      LambdaExpr lambdaExpr = (LambdaExpr) expression;
      com.github.javaparser.ast.stmt.Statement lambdaBody = lambdaExpr.getBody();
      io.sundr.model.Statement bodyStatement;

      // Check if the lambda body is an expression statement containing just an expression
      if (lambdaBody instanceof com.github.javaparser.ast.stmt.ExpressionStmt) {
        com.github.javaparser.ast.stmt.ExpressionStmt exprStmt = (com.github.javaparser.ast.stmt.ExpressionStmt) lambdaBody;
        // For expression lambdas, create a return statement
        io.sundr.model.Expression expr = convertExpression(exprStmt.getExpression());
        bodyStatement = new Return(expr);
      } else {
        // For block lambdas, convert normally
        bodyStatement = StatementConverter.convertStatement(lambdaBody);
      }

      return new Lambda(lambdaExpr.getParameters().stream().map(Parameter::getId).map(VariableDeclaratorId::getName)
          .collect(Collectors.toList()), bodyStatement);
    } else if (expression instanceof EnclosedExpr) {
      EnclosedExpr enclosedExpr = (EnclosedExpr) expression;
      return new Enclosed(convertExpression(enclosedExpr.getInner()));
    } else if (expression instanceof InstanceOfExpr) {
      InstanceOfExpr instanceOfExpr = (InstanceOfExpr) expression;
      com.github.javaparser.ast.type.Type type = instanceOfExpr.getType();
      if (type instanceof com.github.javaparser.ast.type.ClassOrInterfaceType) {
        return new InstanceOf(convertExpression(instanceOfExpr.getExpr()),
            (ClassRef) SOURCE_ADAPTER.getReferenceAdapterFunction()
                .apply((com.github.javaparser.ast.type.ClassOrInterfaceType) type));
      } else {
        // Handle other reference types by converting to TypeRef first
        TypeRef typeRef = TYPEREF_ADAPTER.apply(type);
        if (typeRef instanceof ClassRef) {
          return new InstanceOf(convertExpression(instanceOfExpr.getExpr()), (ClassRef) typeRef);
        } else {
          // For non-class types, fall back to Object
          return new InstanceOf(convertExpression(instanceOfExpr.getExpr()), OBJECT);
        }
      }
    } else if (expression instanceof ArrayAccessExpr) {
      ArrayAccessExpr arrayAccessExpr = (ArrayAccessExpr) expression;
      return new Index(convertExpression(arrayAccessExpr.getName()), convertExpression(arrayAccessExpr.getIndex()));
    } else if (expression instanceof ConditionalExpr) {
      ConditionalExpr conditionalExpr = (ConditionalExpr) expression;
      return new Ternary(
          convertExpression(conditionalExpr.getCondition()),
          convertExpression(conditionalExpr.getThenExpr()),
          convertExpression(conditionalExpr.getElseExpr()));
    } else if (expression instanceof MethodReferenceExpr) {
      MethodReferenceExpr methodReferenceExpr = (MethodReferenceExpr) expression;
      String methodName = methodReferenceExpr.getIdentifier();
      List<TypeRef> parameters = new ArrayList<>();
      List<io.sundr.model.Expression> arguments = new ArrayList<>();
      io.sundr.model.Expression scope = methodReferenceExpr.getScope() != null
          ? convertExpression(methodReferenceExpr.getScope())
          : null;
      return new MethodCall(methodName, scope, parameters, arguments);
    } else if (expression instanceof VariableDeclarationExpr) {
      VariableDeclarationExpr varDeclExpr = (VariableDeclarationExpr) expression;
      // For variable declarations in expression context, we create a simple declaration
      // without trying to resolve the full type, to avoid context issues
      if (!varDeclExpr.getVars().isEmpty()) {
        VariableDeclarator var = varDeclExpr.getVars().get(0);
        String varName = var.getId().getName();
        Property prop = Property.newProperty(OBJECT, varName);

        if (var.getInit() != null) {
          io.sundr.model.Expression initExpr = convertExpression(var.getInit());
          return new Declare(prop, initExpr);
        } else {
          return new Declare(prop);
        }
      }
      return null;
    } else if (expression instanceof FieldAccessExpr) {
      FieldAccessExpr fieldAccessExpr = (FieldAccessExpr) expression;
      String fieldName = fieldAccessExpr.getFieldExpr().getName();
      io.sundr.model.Expression scope = convertExpression(fieldAccessExpr.getScope());
      return new PropertyRef(fieldName, scope);
    } else if (expression instanceof TypeExpr) {
      TypeExpr typeExpr = (TypeExpr) expression;
      // TypeExpr represents a type reference in expression context (like in method references)
      // For now, convert to a ContextRef with the type name
      return new ContextRef(typeExpr.getType().toString());
    } else if (expression instanceof ClassExpr) {
      ClassExpr classExpr = (ClassExpr) expression;
      // ClassExpr represents .class expressions like String.class
      TypeRef typeRef = TYPEREF_ADAPTER.apply(classExpr.getType());
      if (typeRef instanceof ClassRef) {
        return ((ClassRef) typeRef).dotClass();
      }
      return new ContextRef(typeRef.toString() + ".class");
    } else if (expression instanceof CastExpr) {
      CastExpr castExpr = (CastExpr) expression;
      TypeRef targetType = TYPEREF_ADAPTER.apply(castExpr.getType());
      io.sundr.model.Expression expr = convertExpression(castExpr.getExpr());
      return new Cast(targetType, expr);
    }
    return null;
  }
}
