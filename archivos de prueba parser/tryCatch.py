def esNumero(int x):
    try:
        print(x)
	;
    except : # No trae el error
        print(0)
	;

def division(int x,int y):
    try:
        a= x/y
        print("EL resultado es", a)
		# No trae el ; del try
    except ZeroDivisionError:
        print("INVALIDO!!! Division entre 0")
	;

#programa

string entrada

entrada = input("Digite una entrada")
if esNumero(entrada):  #La llamada a una funcion es una exp
    print(entrada, "es un numero")
;
else:
    print(entrada, "No es un numero")
;
division(8,0)

