import sympy as sp
import matplotlib.pyplot as plt

def euler_simple(func, y0, x0, xn, h):
    """
    Método de Euler simples para resolver uma equação diferencial ordinária.

    Args:
    func (function): A função que representa a EDO dy/dx = func(x, y).
    y0 (float): O valor inicial de y (y(x0)).
    x0 (float): O valor inicial de x.
    xn (float): O valor final de x.
    h (float): O tamanho do passo.

    Returns:
    list: Lista de tuplas (x, y) com a solução aproximada da EDO.
    """
    x_values = [x0]
    y_values = [y0]
    x = x0
    y = y0

    while x < xn:
        y += h * func(x, y)
        x += h
        x_values.append(x)
        y_values.append(y)

    return x_values, y_values

def euler_improved(func, y0, x0, xn, h):
    """
    Método de Euler aperfeiçoado para resolver uma equação diferencial ordinária.

    Args:
    func (function): A função que representa a EDO dy/dx = func(x, y).
    y0 (float): O valor inicial de y (y(x0)).
    x0 (float): O valor inicial de x.
    xn (float): O valor final de x.
    h (float): O tamanho do passo.

    Returns:
    list: Lista de tuplas (x, y) com a solução aproximada da EDO.
    """
    x_values = [x0]
    y_values = [y0]
    x = x0
    y = y0

    while x < xn:
        y_pred = y + h * func(x, y)
        y += 0.5 * h * (func(x, y) + func(x + h, y_pred))
        x += h
        x_values.append(x)
        y_values.append(y)

    return x_values, y_values

def runge_kutta(func, y0, t0, tn, h):
    """
    Método de Runge-Kutta de quarta ordem para resolver uma equação diferencial ordinária.

    Args:
    func (function): A função que representa a EDO dy/dx = func(x, y).
    y0 (float): O valor inicial de y (y(x0)).
    x0 (float): O valor inicial de x.
    xn (float): O valor final de x.
    h (float): O tamanho do passo.

    Returns:
    list: Lista de tuplas (x, y) com a solução aproximada da EDO.
    """
    x_values = [x0]
    y_values = [y0]
    x = x0
    y = y0

    while x < xn:
        k1 = h * func(x, y)
        k2 = h * func(x + 0.5 * h, y + 0.5 * k1)
        k3 = h * func(x + 0.5 * h, y + 0.5 * k2)
        k4 = h * func(x + h, y + k3)

        y += (k1 + 2 * k2 + 2 * k3 + k4) / 6
        x += h
        x_values.append(x)
        y_values.append(y)

    return x_values, y_values

def plot_solution(x_values, y_values, title):
    """
    Plota o gráfico da solução aproximada de uma EDO.

    Args:
    x_values (list): Lista dos valores do eixo x.
    y_values (list): Lista dos valores de y correspondentes aos valores de x.
    title (str): Título do gráfico.
    """
    plt.plot(x_values, y_values, label='Aproximação')
    plt.xlabel('x')
    plt.ylabel('y')
    plt.title(title)
    plt.legend()
    plt.grid(True)
    plt.show()

def main():
    x0 = float(input("Digite o x inicial x0: "))
    xn = float(input("Digite o x final xn: "))
    y0 = float(input("Digite o y inicial y(x0): "))
    h = float(input("Digite o tamanho do passo h: "))

    func_input = input("Digite a função f(x, y): ")
    x, y = sp.symbols('x y')
    func = sp.lambdify((x, y), func_input, 'numpy')

    opc = int(input("\n[1] Método de Euler Simples\n[2] Método de Euler Aperfeiçoado (Runge Kutta de 2ª Ordem)\n[3] Método de Runge Kutta de 4ª Ordem\n[0] Sair\nEscolha um método de solução de EDO: "))

    while opc != 0:
        if opc == 1:
            x_values, y_values = euler_simple(func, y0, x0, xn, h)
            y_values.reverse()
            print(y_values[0])
            plot_solution(x_values, y_values, 'Método de Euler Simples')
        elif opc == 2:
            x_values, y_values = euler_simple(func, y0, x0, xn, h)
            y_values.reverse()
            print(y_values[0])
            plot_solution(x_values, y_values, 'Método de Euler Aperfeiçoado')
        elif opc == 3:
            x_values, y_values = euler_simple(func, y0, x0, xn, h)
            y_values.reverse()
            print(y_values[0])
            plot_solution(x_values, y_values, 'Método de Runge Kutta')

        opc = int(input("\n[1] Método de Euler Simples\n[2] Método de Euler Aperfeiçoado (Runge Kutta de 2ª Ordem)\n[3] Método de Runge Kutta de 4ª Ordem\n[0] Sair\nEscolha um método de solução de EDO: "))

    print("Programa encerrado.")

if __name__ == "__main__":
    main()
