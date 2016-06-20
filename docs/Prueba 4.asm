section .data
	temp1 dw 0
	var_fun_p dd 0
	var_fun_q db 0
	var_fun_wq db 0
	var_fun_z dd 0
	var_fun_m db 0
	var_fun_n db 0
	var_fun_o db 0
	temp2 dw 0
	temp3 dw 0
	temp4 dw 0
	var_p dd 0
	var_q db 0
	var_e db 0
	var_x dw 0
	var_i dw 0
	var_wq db 0
	var_z dd 0
	var_m db 0
	var_n db 0
	var_o db 0
	temp5 dw 0
	temp6 dw 0
	temp7 dw 0
	temp8 dw 0
	temp9 dw 0
	temp10 dw 0
section .text
	global _start
pow:
Label-IF-ELSE0:
;operaciones para obtener el valor boleano
	;cmp eax, resultadoBooleano
	je Label-IF-ELSE2
	jne Label-IF-ELSE3
Label-IF-ELSE2:
	mov eax,null
	add eax,6
	mov temp1,eax
	mov eax,temp1
	add eax,null
	mov temp1,eax
	mov null, temp1
	jmp Label-IF-ELSE1
Label-IF-ELSE3:
;operaciones para obtener el valor boleano
	;cmp eax, resultadoBooleano
	je Label-IF-ELSE4
	jne Label-IF-ELSE1
Label-IF-ELSE4:
	mov eax,null
	add eax,null
	mov temp2,eax
	mov null, temp2
	mov eax,5
	imul eax,null
	mov temp2,eax
	mov null, temp2
	jmp Label-IF-ELSE1
Label-IF-ELSE1:
	ret
hola:
	mov eax,63
	idiv 8
	mov temp3,eax
	mov eax,2
	add eax,9
	mov temp4,eax
	mov eax,temp4
	add eax,8
	mov temp2,eax
	mov eax,temp2
	add eax,temp3
	mov temp2,eax
	mov null, temp2
Label-IF-ELSE5:
;operaciones para obtener el valor boleano
	;cmp eax, resultadoBooleano
	je Label-IF-ELSE7
	jne Label-IF-ELSE6
Label-IF-ELSE7:
	mov eax,3
	add eax,null
	mov temp5,eax
	mov null, temp5
	jmp Label-IF-ELSE6
Label-IF-ELSE6:
	ret
_start:
	mov var_x, 5
	mov var_i, 0
Label-IF-ELSE8:
;operaciones para obtener el valor boleano
	;cmp eax, resultadoBooleano
	je Label-IF-ELSE10
	jne Label-IF-ELSE11
Label-IF-ELSE10:
	mov eax,3
	add eax,4
	mov temp7,eax
	mov eax,6
	imul eax,42
	mov temp8,eax
	mov eax,1
	add eax,temp8
	mov temp6,eax
	mov eax,temp6
	imul eax,temp7
	mov temp5,eax
	mov eax,temp5
	idiv 5
	mov temp5,eax
	mov var_i, temp5
	jmp Label-IF-ELSE9
Label-IF-ELSE11:
Label-IF-ELSE13:
;operaciones para obtener el valor boleano
	;cmp eax, resultadoBooleano
	je Label-IF-ELSE15
	jne Label-IF-ELSE16
Label-IF-ELSE15:
	mov var_i, 9
	jmp Label-IF-ELSE14
Label-IF-ELSE16:
;operaciones para obtener el valor boleano
	;cmp eax, resultadoBooleano
	je Label-IF-ELSE17
	jne Label-IF-ELSE18
Label-IF-ELSE17:
	mov eax,0
	add eax,var_i
	mov temp9,eax
	mov var_i, temp9
	jmp Label-IF-ELSE14
Label-IF-ELSE18:
	mov eax,8
	add eax,9
	mov temp9,eax
	mov var_x, temp9
Label-IF-ELSE14:
	mov var_i, 8
Label-IF-ELSE9:
	mov eax,8
	imul eax,9
	mov temp10,eax
	mov eax,temp10
	add eax,6
	mov temp9,eax
	mov eax,temp9
	imul eax,var_i
	mov temp9,eax
	mov var_i, temp9
