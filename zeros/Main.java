package zeros;

import java.util.Locale;
import java.util.Scanner;
import static zeros.Numeric.*;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner leia = new Scanner(System.in);

        String menu = "\nEscolha um metodo:\n[1] Metodo da Bisseccao\n[2] Metodo da Falsa Posicao\n[3] Metodo do Ponto Fixo\n[4] Metodo de Newton/Tangente\n[5] Metodo da Secante\n[6] Alterar a funcao\n[0] Encerrar";

        System.out.print("Digite a funcao f(x) = ");
        String function = leia.nextLine();
        System.out.println(menu);
        int opt;
        while ((opt = leia.nextInt()) != 0) {
            switch (opt) {
                case 1:
                    try {
                        System.out.print("Digite um intervalo a b: ");
                        double a = leia.nextDouble(), b = leia.nextDouble();
                        System.out.print("Digite uma precisao/epsilon: ");
                        double e = leia.nextDouble();
                        System.out.println("Metodo da Bisseccao: " + bissection(function, a, b, e));
                    } catch (ArithmeticException e) {
                        System.err.println(e);
                    } catch (NumberFormatException e) {
                        System.err.println("O método não convergiu.");
                    }
                    break;
                case 2:
                    try {
                        System.out.print("Digite um intervalo a b: ");
                        double a = leia.nextDouble();
                        double b = leia.nextDouble();
                        System.out.print("Digite uma precisao/epsilon: ");
                        double e = leia.nextDouble();
                        System.out.println("Metodo da Falsa Posicao: " + falsePosition(function, a, b, e, e));
                    } catch (ArithmeticException e) {
                        System.err.println(e);
                    } catch (NumberFormatException e) {
                        System.err.println("O método não convergiu.");
                    }
                    break;
                case 3:
                    try {
                        System.out.print("Digite a funcao de iteracao: ");
                        leia.nextLine();
                        String mpf = leia.nextLine();
                        System.out.print("Digite x0: ");
                        double x0 = leia.nextDouble();
                        System.out.print("Digite uma precisao/epsilon: ");
                        double e = leia.nextDouble();
                        System.out.println("Metodo do Ponto Fixo: " + fixedPoint(function, mpf, x0, e, e));
                    } catch (ArithmeticException e) {
                        System.err.println(e);
                    } catch (NumberFormatException e) {
                        System.err.println("O método não convergiu.");
                    }
                    break;
                case 4:
                    try {
                        System.out.print("Digite x0: ");
                        double x0 = leia.nextDouble();
                        System.out.print("Digite uma precisao/epsilon: ");
                        double e = leia.nextDouble();
                        System.out.println("Metodo de Newton/Tangente: " + newton(function, x0, e, e));
                    } catch (ArithmeticException e) {
                        System.err.println(e);
                    } catch (NumberFormatException e) {
                        System.err.println("O método não convergiu.");
                    }
                    break;
                case 5:
                    try {
                        System.out.print("Digite x0: ");
                        double x0 = leia.nextDouble();
                        System.out.print("Digite x1: ");
                        double x1 = leia.nextDouble();
                        System.out.print("Digite uma precisao/epsilon: ");
                        double e = leia.nextDouble();
                        System.out.println("Metodo da Secante: " + secant(function, x0, x1, e, e));
                    } catch (ArithmeticException e) {
                        System.err.println(e);
                    } catch (NumberFormatException e) {
                        System.err.println("O método não convergiu.");
                    }
                    break;
                case 6:
                    System.out.print("Digite a nova funcao: ");
                    leia.nextLine();
                    function = leia.nextLine();
            }

            System.out.println(menu);
        }
    }
}
