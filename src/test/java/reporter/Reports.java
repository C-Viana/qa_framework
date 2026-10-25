package reporter;

public final class Reports {
    private static final ReporterInterface INSTANCE = new ExtentReportsImpl();

    private Reports() {}

    public static ReporterInterface get() { return INSTANCE; }
}
