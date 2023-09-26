package io.sundr.jakarta;

import io.sundr.model.ClassRef;
import io.sundr.model.TypeDef;
import io.sundr.reflect.ClassTo;
import jakarta.transaction.Transaction;

public class JakartaTransaction {

  public static TypeDef TRANSACTION_DEF = ClassTo.TYPEDEF.apply(Transaction.class);
  public static ClassRef TRANSACTION_REF = TRANSACTION_DEF.toInternalReference();
}
