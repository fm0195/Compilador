section .data
	temp_float dd 0
	var_a db 0
	var_b db 0
section .text
	global _start
_start:
Label-IF-ELSE0:
	;operaciones para obtener el valor boleano
	;cmp eax, resultadoBooleano
	je Label-IF-ELSE2
	jne Label-IF-ELSE1
Label-IF-ELSE2:
	jmp Label-IF-ELSE1
Label-IF-ELSE1:
