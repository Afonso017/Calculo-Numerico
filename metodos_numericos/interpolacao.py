def lagrange_interpolation(x_values, y_values, x):
    n = len(x_values)
    result = 0.0

    for i in range(n):
        term = y_values[i]
        for j in range(n):
            if j != i:
                term *= (x - x_values[j]) / (x_values[i] - x_values[j])
        result += term

    return result

def newton_interpolation(x_values, y_values, x):
    n = len(x_values)
    coefficients = y_values.copy()

    for j in range(1, n):
        for i in range(n - 1, j - 1, -1):
            coefficients[i] = (coefficients[i] - coefficients[i - 1]) / (x_values[i] - x_values[i - j])

    result = coefficients[n - 1]

    for i in range(n - 2, -1, -1):
        result = result * (x - x_values[i]) + coefficients[i]

    return result

x_input = input("Digite os valores de x: ")
x_values = list(map(float, x_input.split()))

y_input = input("Digite os valores de y: ")
y_values = list(map(float, y_input.split()))

opc = int(input("\n[1] Lagrange\n[2] Newton\n[0] Sair\nEscolha um método de interpolação: "))

while opc != 0:
	if opc == 1:
		x = float(input("\nDigite um valor para x: "))
		print("A aproximação, usando o método de Lagrange, é igual a: ", end="")
		print(lagrange_interpolation(x_values, y_values, x))
	elif opc == 2:
		x = float(input("\nDigite um valor para x: "))
		print("A aproximação, usando o método de Newton, é igual a: ", end="")
		print(newton_interpolation(x_values, y_values, x))

	opc = int(input("\n[1] Lagrange\n[2] Newton\n[0] Sair\nEscolha um método de interpolação: "))
		
print("Programa encerrado.")
