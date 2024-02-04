package zeros;

import static zeros.Calculator.*;

public class Numeric {

    private static String func;
    private static String mpf;

    private static double f(double x) {
        return Double.parseDouble(solve(func, x));
    }

    private static double pf(double x) {
        return Double.parseDouble(solve(mpf, x));
    }
    
    public static String solve(String func, double x) throws ArithmeticException {
        func = func.replace(" ", "");
        func = func.replace("x", String.valueOf(x));
        func = func.replace("e", String.valueOf(Math.E));
        func = func.replace(String.valueOf((char) 960), String.valueOf(Math.PI));
        func = func.replace("+-", "-");
        func = func.replace("--", "+");

        while (func.contains("(")) {
            int end = func.indexOf(")");
            char[] ch = func.toCharArray();
            int i = end - 1;
            while (ch[i--] != '(') {
            }
            String resolved = solve(func.substring(i + 2, end), x);

            func = func.replace("(" + func.substring(i + 2, end) + ")", resolved);
            func = func.replace("+-", "-");
            func = func.replace("--", "+");
        }

        while (func.contains("^")) {
            func = pot(func);
            func = func.replace("+-", "-");
            func = func.replace("--", "+");
        }

        while (func.contains("sen") || func.contains("cos")) {
            if (func.contains("sen") && func.contains("cos")) {
                if (func.indexOf("sen") < func.indexOf("cos")) {
                    func = sen(func);
                } else {
                    func = cos(func);
                }
            } else if (func.contains("sen")) {
                func = sen(func);
            } else {
                func = cos(func);
            }
            func = func.replace("+-", "-");
            func = func.replace("--", "+");
        }

        while (func.contains("sqrt") || func.contains("log")) {
            if (func.contains("sqrt") && func.contains("log")) {
                if (func.indexOf("sqrt") < func.indexOf("log")) {
                    func = sqrt(func);
                } else {
                    func = log10(func);
                }
            } else if (func.contains("sqrt")) {
                func = sqrt(func);
            } else {
                func = log10(func);
            }
            func = func.replace("+-", "-");
            func = func.replace("--", "+");
        }
        
        while (func.contains("*") || func.contains("/")) {
            if (func.contains("*") && func.contains("/")) {
                if (func.indexOf("*") < func.indexOf("/")) {
                    func = mult(func);
                } else {
                    func = div(func);
                }
            } else if (func.contains("*")) {
                func = mult(func);
            } else {
                func = div(func);
            }
            func = func.replace("+-", "-");
            func = func.replace("--", "+");
        }
        
        while (func.substring(1).contains("-") && func.charAt(func.indexOf("-", 1) - 1) != 'E') {
            func = func.replace("+-", "-");
            func = func.replace("--", "+");
            func = sub(func);
        }

        while (func.substring(1).contains("+")) {
            func = sum(func);
        }

        if (func.charAt(0) == '+') {
            func = func.substring(1);
        }

        return func;
    }

    public static double bissection(String function, double a, double b, double e) {
        func = function;

        if (Math.abs(b - a) < e || Math.abs(f(a)) < e || Math.abs(f(b)) < e) {
            System.out.println("k = 0");
            return a;
        }

        double x, m;
        int k = (int) Math.ceil((Math.log(b - a) - Math.log(e)) / Math.log(2));

        for (int i = 1; i <= k + 1; i++) {
            x = (a + b) / 2;
            m = f(x);

            if (Math.abs(b - a) < e) {
                System.out.println("k = " + i);
                return a;
            } else if (Math.abs(m) < e) {
                System.out.println("k = " + i);
                return x;
            }

            if (f(a) * m < 0) {
                b = x;
            } else {
                a = x;
            }
        }

        System.err.println("Nao foi encontrada raiz no intervalo e precisao especificados");
        return Double.NaN;
    }

    public static double falsePosition(String function, double a, double b, double e1, double e2) {
        func = function;

        double x, m, fa = f(a), fb = f(b);

        if (Math.abs(fa) < e2) {
            System.out.println("k = 0");
            return a;
        } else if (Math.abs(fb) < e2) {
            System.out.println("k = 0");
            return b;
        }

        for (int k = 1; k <= 1000; k++) {
            x = (a * fb - b * fa) / (fb - fa);
            m = f(x);

            if (Math.abs(m) < e2 || Math.abs(b - a) < e1) {
                System.out.println("k = " + k);
                return x;
            }

            if (m * fa < 0) {
                b = x;
                fb = m;
            } else {
                a = x;
                fa = m;
            }
        }

        System.err.println("Numero maximo de iteracoes atingido. Nao foi encontrada raiz no intervalo com a precisao especificada.");
        return Double.NaN;
    }

    public static double fixedPoint(String function, String pontoFixo, double x, double e1, double e2) {
        func = function;
        mpf = pontoFixo;

        int i = 0;
        double x0 = 0;
        do {
            if (Math.abs(f(x)) < e1 || Math.abs(x - x0) < e2) {
                System.out.println("k = " + i);
                return x;
            }
            x0 = x;
            x = pf(x);
        } while (i++ <= 1000);

        System.err.println("Nao foi encontrada raiz com os dados iniciais especificados");
        return Double.NaN;
    }

    public static double newton(String function, double x0, double h, double e) {
        func = function;

        if (Math.abs(f(x0)) < e) {
            System.out.println("k = 0");
            return x0;
        }

        int k = 1;
        double x, dx, fx;
        do {
            if ((dx = (f(x0 + h) - f(x0 - h)) / (2 * h)) != 0) {
                x = x0 - (f(x0) / dx);
            } else {
                throw new ArithmeticException("Divisao por zero");
            }
            fx = f(x);

            if (Math.abs(fx) < e || Math.abs(x - x0) < e) {
                System.out.println("k = " + k);
                return x;
            }

            x0 = x;
        } while (k++ <= 1000);

        System.err.println("Nao foi encontrada raiz com os dados iniciais especificados");
        return Double.NaN;
    }

    public static double secant(String function, double x0, double x1, double e1, double e2) {
        func = function;

        int k = 0;
        double x, fx0, fx1;
        do {
            fx0 = f(x0);
            fx1 = f(x1);
            x = (x0 * fx1 - x1 * fx0) / (fx1 - fx0);

            if (Math.abs(fx0) < e1) {
                System.out.println("k = " + k);
                return x0;
            } else if (Math.abs(fx1) < e1 || Math.abs(x1 - x0) < e2) {
                System.out.println("k = " + k);
                return x1;
            }

            x0 = x1;
            x1 = x;
        } while (k++ <= 1000);

        System.err.println("Nao foi encontrada raiz com os dados iniciais especificados");
        return Double.NaN;
    }
    
}
