package zeros;

public class Calculator {
    
	private static int idx = 0;
	private static double result = 0;
	private static String[] temp1, temp2;
	private static String replace;
    
	public static String sum(String func) {
		String str1 = func.substring(0, func.indexOf("+", 1));
		String str2 = func.substring(func.indexOf("+", 1) + 1);

		temp1 = str1.split("\\+|(?<=\\d)-");
		temp2 = str2.split("\\+|(?<=\\d)-");

		result = Double.parseDouble(temp1[temp1.length - 1]) + Double.parseDouble(temp2[0]);

		replace = temp1[temp1.length - 1] + "+" + temp2[0];
		return func.replace(replace, String.valueOf(result));
	}
    
	public static String sub(String func) {
		String str1 = func.substring(0, func.indexOf("-", 1));
		String str2 = func.substring(func.indexOf("-", 1));
		
		temp1 = str1.split("\\+|(?<=\\d)-");
		temp2 = str2.split("\\+|(?<=\\d)-");
		
		double n1 = Double.parseDouble(temp1[temp1.length-1]);
		double n2 = Double.parseDouble(temp2[0]);
		
        result = n1 - Math.abs(n2);
        
		replace = temp1[temp1.length - 1] + temp2[0];
		return func.replace(replace, String.valueOf(result));
	}
    
	public static String mult(String func) {
		String str1 = func.substring(0, func.indexOf("*", 1));
		String str2 = func.substring(func.indexOf("*", 1) + 1);

		temp1 = str1.split("[+*/]|(?<=\\d)-");
		temp2 = str2.split("[+*/]|(?<=\\d)-");

		result = Double.parseDouble(temp1[temp1.length - 1]) * Double.parseDouble(temp2[0]);

		replace = temp1[temp1.length - 1] + "*" + temp2[0];
		return func.replace(replace, String.valueOf(result));
	}
    
	public static String div(String func) {
		String str1 = func.substring(0, func.indexOf("/", 1));
		String str2 = func.substring(func.indexOf("/", 1) + 1);

		temp1 = str1.split("[+*/]|(?<=\\d)-");
		temp2 = str2.split("[+*/]|(?<=\\d)-");

		if (Double.parseDouble(temp2[0]) != 0) {
			result = Double.parseDouble(temp1[temp1.length - 1]) / Double.parseDouble(temp2[0]);
		} else {
			throw new ArithmeticException("Nao e possivel dividir por 0.");
		}

		replace = temp1[temp1.length - 1] + "/" + temp2[0];
		return func.replace(replace, String.valueOf(result));
	}
    
	public static String pot(String func) {
		idx = func.indexOf("^");
		String str1 = func.substring(0, func.indexOf("^", 1));
		String str2 = func.substring(func.indexOf("^", 1) + 1);
		
		temp1 = str1.split("[+*/^]|(?<=\\d)-");
		temp2 = str2.split("[+*/^]|(?<=\\d)-");
		
		double base = Double.parseDouble(temp1[temp1.length - 1]);
		double exponent = Double.parseDouble(temp2[0]);
		result = Math.pow(base, exponent);
		
		replace = temp1[temp1.length - 1] + "^" + temp2[0];
		return func.replace(replace, String.valueOf(result));
	}
    
	public static String log10(String func) {
		idx = func.indexOf("log") + 3;
		temp1 = func.substring(0, idx).split("[^-0-9.]");
		
		double l = Double.parseDouble(temp1[0]);
		if (l > 0) {
			result = Math.log10(l);
		} else {
			throw new ArithmeticException("Nao existe log de numero negativo ou zero.");
		}
		
		return func.replaceFirst("log" + temp1[0], String.valueOf(result));
	}
    
	public static String sqrt(String func) {
		idx = func.indexOf("sqrt");
		temp1 = func.substring(idx + 4).split("[^-0-9.]");

		double l = Double.parseDouble(temp1[0]);
		if (l > 0) {
			result = Math.sqrt(l);
		} else {
			throw new ArithmeticException("Nao ha raiz quadrada real de numero negativo.");
		}

		return func.replaceFirst("sqrt" + temp1[0], String.valueOf(result));
	}
    
	public static String cos(String func) {
		idx = func.indexOf("cos");
		String str1 = func.substring(idx + 3);
		
		temp1 = str1.split("\\+|(?<=\\d)-");
		result = Math.cos(Double.parseDouble(temp1[0]));
		
		return func.replaceFirst("cos" + temp1[0], String.valueOf(result));
	}
    
	public static String sen(String func) {
		idx = func.indexOf("sen");
		String str1 = func.substring(idx + 3);
		
		temp1 = str1.split("\\+|(?<=\\d)-");
		result = Math.cos(Double.parseDouble(temp1[0]));
		
		return func.replaceFirst("sen" + temp1[0], String.valueOf(result));
	}
	
}
