package com.icdenge.config.jpa;

import org.hibernate.dialect.PostgreSQLDialect;

public class MyPostgresqlDialect extends PostgreSQLDialect {
  /**
   * We do not want enum database checks
   * @param columnName
   * @param values
   * @return
   */
  @Override
  public String getCheckCondition(String columnName, String[] values) {
    return null;
  }
}
