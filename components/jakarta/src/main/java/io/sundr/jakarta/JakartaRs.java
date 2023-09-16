package io.sundr.jakarta;

import io.sundr.model.ClassRef;
import io.sundr.model.TypeDef;
import io.sundr.reflect.ClassTo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class JakartaRs {

  public static TypeDef PATH_DEF = ClassTo.TYPEDEF.apply(Path.class);
  public static ClassRef PATH_REF = PATH_DEF.toInternalReference();

  public static TypeDef PRODUCES_DEF = ClassTo.TYPEDEF.apply(Produces.class);
  public static ClassRef PRODUCES_REF = PRODUCES_DEF.toInternalReference();

  public static TypeDef CONSUMES_DEF = ClassTo.TYPEDEF.apply(Consumes.class);
  public static ClassRef CONSUMES_REF = CONSUMES_DEF.toInternalReference();

  public static TypeDef MEDIATYPE_DEF = ClassTo.TYPEDEF.apply(MediaType.class);
  public static ClassRef MEDIATYPE_REF = MEDIATYPE_DEF.toInternalReference();

  public static TypeDef RESPONSE_DEF = ClassTo.TYPEDEF.apply(Response.class);
  public static ClassRef RESPONSE_REF = RESPONSE_DEF.toInternalReference();

  public static TypeDef PATH_PARAM_DEF = ClassTo.TYPEDEF.apply(PathParam.class);
  public static ClassRef PATH_PARAM_REF = PATH_PARAM_DEF.toInternalReference();

  public static TypeDef QUERY_PARAM_DEF = ClassTo.TYPEDEF.apply(QueryParam.class);
  public static ClassRef QUERY_PARAM_REF = QUERY_PARAM_DEF.toInternalReference();

  public static TypeDef GET_DEF = ClassTo.TYPEDEF.apply(GET.class);
  public static ClassRef GET_REF = GET_DEF.toInternalReference();

  public static TypeDef POST_DEF = ClassTo.TYPEDEF.apply(POST.class);
  public static ClassRef POST_REF = POST_DEF.toInternalReference();

  public static TypeDef PUT_DEF = ClassTo.TYPEDEF.apply(PUT.class);
  public static ClassRef PUT_REF = PUT_DEF.toInternalReference();

  public static TypeDef DELETE_DEF = ClassTo.TYPEDEF.apply(DELETE.class);
  public static ClassRef DELETE_REF = DELETE_DEF.toInternalReference();

  public static TypeDef WEB_APPLICATION_EXCEPTION_DEF = ClassTo.TYPEDEF.apply(WebApplicationException.class);
  public static ClassRef WEB_APPLICATION_EXCEPTION_REF = WEB_APPLICATION_EXCEPTION_DEF.toInternalReference();
}
