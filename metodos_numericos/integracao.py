import sympy as sp

def riemann_sum(func, a, b, n, method='left'):
    """
    Calcula a soma de Riemann de uma função em um intervalo [a, b].

    Args:
    func (function): A função a ser integrada.
    a (float): O limite inferior do intervalo.
    b (float): O limite superior do intervalo.
    n (int): O número de subintervalos para dividir o intervalo [a, b].
    method (str): Método de cálculo da soma de Riemann ('left', 'right', 'middle', 'random').

    Returns:
    float: O valor aproximado da integral.
    """
    delta_x = (b - a) / n
    result = 0.0

    for i in range(n):
        if method == 'left':
            x = a + i * delta_x
        elif method == 'right':
            x = a + (i + 1) * delta_x
        elif method == 'middle':
            x = a + (i + 0.5) * delta_x
        elif method == 'random':
            x = a + (i + random.random()) * delta_x
        else:
            raise ValueError("Método inválido. Use 'left', 'right', 'middle', ou 'random'.")

        result += func(x) * delta_x

    return result


def trapezoidal_rule(func, a, b, n):
    """
    Calcula a integral de uma função usando a regra do trapézio.

    Args:
    func (function): A função a ser integrada.
    a (float): O limite inferior do intervalo.
    b (float): O limite superior do intervalo.
    n (int): O número de subintervalos para dividir o intervalo [a, b].

    Returns:
    float: O valor aproximado da integral.
    """
    delta_x = (b - a) / n
    result = 0.5 * (func(a) + func(b))

    for i in range(1, n):
        result += func(a + i * delta_x)

    result *= delta_x
    return result


def simpson_one_third_rule(func, a, b, n):
    """
    Calcula a integral de uma função usando a regra de Simpson 1/3.

    Args:
    func (function): A função a ser integrada.
    a (float): O limite inferior do intervalo.
    b (float): O limite superior do intervalo.
    n (int): O número de subintervalos para dividir o intervalo [a, b].

    Returns:
    float: O valor aproximado da integral.
    """
    if n % 2 != 0:
        raise ValueError("O número de subintervalos deve ser par para a regra de Simpson 1/3.")

    delta_x = (b - a) / n
    result = func(a) + func(b)

    for i in range(1, n):
        x = a + i * delta_x
        result += 4 * func(x) if i % 2 != 0 else 2 * func(x)

    result *= delta_x / 3
    return result


def simpson_three_eighth_rule(func, a, b, n):
    """
    Calcula a integral de uma função usando a regra de Simpson 3/8.

    Args:
    func (function): A função a ser integrada.
    a (float): O limite inferior do intervalo.
    b (float): O limite superior do intervalo.
    n (int): O número de subintervalos para dividir o intervalo [a, b].

    Returns:
    float: O valor aproximado da integral.
    """
    if n % 3 != 0:
        raise ValueError("O número de subintervalos deve ser múltiplo de 3 para a regra de Simpson 3/8.")

    delta_x = (b - a) / n
    result = func(a) + func(b)

    for i in range(1, n):
        x = a + i * delta_x
        result += 3 * (func(x) if i % 3 == 0 else 2 * func(x))

    result *= 3 * delta_x / 8
    return result

def main():
    a = float(input("Digite o limite inferior do intervalo: "))
    b = float(input("Digite o limite superior do intervalo: "))

    func_input = input("Digite a função f(x): ")
    x = sp.symbols('x')
    func = sp.lambdify((x), func_input, 'numpy')

    opc = int(input("\n[1] Soma de Riemann\n[2] Método do Trapézio\n[3] Método 1/3 de Simpson\n[4] Método 3/8 de Simpson\n[0] Sair\nEscolha um método de integração: "))

    while opc != 0:
        if opc == 1:
            method = input("\nEscolha o método de cálculo da Soma de Riemann ('left', 'right', 'middle', 'random'): ")
            n = int(input("Digite o número de subintervalos: "))
            print("Aproximação da integral usando a Soma de Riemann: ", riemann_sum(func, a, b, n, method))
        elif opc == 2:
            n = int(input("Digite o número de subintervalos: "))
            print("Aproximação da integral usando o Método do Trapézio: ", trapezoidal_rule(func, a, b, n))
        elif opc == 3:
            n = int(input("Digite o número de subintervalos (númnero par de subintervalos): "))
            print("Aproximação da integral usando o Método 1/3 de Simpson: ", simpson_one_third_rule(func, a, b, n))
        elif opc == 4:
            n = int(input("Digite o número de subintervalos (número de subintervalos múltiplo de 3): "))
            print("Aproximação da integral usando o Método 3/8 de Simpson: ", simpson_three_eighth_rule(func, a, b, n))

        opc = int(input("\n[1] Soma de Riemann\n[2] Método do Trapézio\n[3] Método 1/3 de Simpson\n[4] Método 3/8 de Simpson\n[0] Sair\nEscolha um método de integração: "))

    print("Programa encerrado.")

if __name__ == "__main__":
    main()
