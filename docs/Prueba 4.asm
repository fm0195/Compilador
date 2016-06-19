section .data
	var_x dw 0
	var_i dw 0
	temp1 dw 0
	temp2 dw 0
	temp3 dw 0
	temp4 dw 0
	temp5 dw 0
section .text
	global _start
_start:
	mov var_x, 0
	mov var_i, 0
Label-IF-ELSE0:
;operaciones para obtener el valor boleano
	;cmp eax, resultadoBooleano
	je Label-IF-ELSE2
	jne Label-IF-ELSE3
Label-IF-ELSE2:
	mov eax,3
	add eax,4
	mov temp3,eax
	mov eax,6
	imul eax,42
	mov temp4,eax
	mov eax,1
	add eax,temp4
	mov temp2,eax
	mov eax,temp2
	imul eax,temp3
	mov temp1,eax
	mov eax,temp1
	idiv 5
	mov temp1,eax
	mov var_i, temp1
	jmp Label-IF-ELSE1
Label-IF-ELSE3:
Label-IF-ELSE5:
;operaciones para obtener el valor boleano
	;cmp eax, resultadoBooleano
	je Label-IF-ELSE7
	jne Label-IF-ELSE8
Label-IF-ELSE7:
	mov var_i, 9
	jmp Label-IF-ELSE6
Label-IF-ELSE8:
;operaciones para obtener el valor boleano
	;cmp eax, resultadoBooleano
	je Label-IF-ELSE9
	jne Label-IF-ELSE10
Label-IF-ELSE9:
	mov var_i, 0
	jmp Label-IF-ELSE6
Label-IF-ELSE10:
	mov eax,8
	add eax,9
	mov temp5,eax
	mov var_x, temp5
Label-IF-ELSE6:
	mov var_i, 8
Label-IF-ELSE1:
	mov eax,8
	imul eax,9
	mov temp5,eax
	mov eax,temp5
	add eax,6
	mov temp5,eax
	mov var_i, temp5
