package io.sundr.model;

import java.lang.Boolean;
import java.lang.String;
import java.lang.StringBuffer;
import java.lang.StringBuilder;

public interface PrimitiveRefFluent<A extends PrimitiveRefFluent<A>> extends TypeRefFluent<A> {

  public String getName();

  public A withName(String name);

  public Boolean hasName();

  public A withNewName(StringBuilder arg1);

  public A withNewName(int[] arg1, int arg2, int arg3);

  public A withNewName(char[] arg1);

  public A withNewName(StringBuffer arg1);

  public A withNewName(byte[] arg1, int arg2);

  public A withNewName(byte[] arg1);

  public A withNewName(char[] arg1, int arg2, int arg3);

  public A withNewName(byte[] arg1, int arg2, int arg3);

  public A withNewName(byte[] arg1, int arg2, int arg3, int arg4);

  public A withNewName(String arg1);

  public int getDimensions();

  public A withDimensions(int dimensions);

  public Boolean hasDimensions();
}
