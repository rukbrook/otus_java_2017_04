-printmapping out/proguard/mapping.txt

-verbose

-optimizationpasses 3
-overloadaggressively
-repackageclasses ''
-allowaccessmodification

-keepclasseswithmembers public class * {
    public static void main(java.lang.String[]);
}