package jp.co.ctc_g.javaee_rest_sample.util.junit;

public @interface TransactionStrategy {
    TransactionStrategyType value() default TransactionStrategyType.POST_COMMIT;
}
