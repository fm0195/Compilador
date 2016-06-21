section .data
	temp_float dd 0
	par_fun_pow_x dw 0
	par_fun_pow_y dw 0
	temp1 dw 0
	par_fun_hola_x dw 0
	par_fun_hola_r db 0
	par_fun_hola_f dd 0
	var_fun_hola_p dd 0
	var_fun_hola_q db 0
	var_fun_hola_wq db 0
	var_fun_hola_z dd 0
	var_fun_hola_m db 0
	var_fun_hola_n db 0
	var_fun_hola_o db 0
	temp2 dw 0
	temp3 dw 0
	temp4 dw 0
	temp5 dw 0
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
	temp6 dw 0
	temp7 dw 0
	temp8 dw 0
	temp9 dw 0
	temp10 dw 0
	temp11 dw 0
section .text
	global _start
pow:
Label-IF-ELSE0:
	;operaciones para obtener el valor boleano
	;cmp eax, resultadoBooleano
	je Label-IF-ELSE2
	jne Label-IF-ELSE3
Label-IF-ELSE2:
	mov eax,par_fun_pow_y
	add eax,6
	mov temp1,eax
	mov eax,temp1
	add eax,par_fun_pow_x
	mov temp1,eax
	mov par_fun_pow_x, temp1
	jmp Label-IF-ELSE1
Label-IF-ELSE3:
	;operaciones para obtener el valor boleano
	;cmp eax, resultadoBooleano
	je Label-IF-ELSE4
	jne Label-IF-ELSE1
Label-IF-ELSE4:
	mov eax,par_fun_pow_x
	add eax,par_fun_pow_y
	mov temp2,eax
	mov par_fun_pow_y, temp2
	mov eax,5
	imul eax,par_fun_pow_x
	mov temp2,eax
	mov par_fun_pow_x, temp2
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
	mov par_fun_hola_x, temp2
Label-IF-ELSE5:
	;operaciones para obtener el valor boleano
	;cmp eax, resultadoBooleano
	je Label-IF-ELSE7
	jne Label-IF-ELSE6
Label-IF-ELSE7:
	fld 3
	fdiv 8
	fstp temp_float
	mov eax,temp_float
	mov temp5,eax
	fld temp5
	fadd par_fun_hola_f
	fstp temp_float
	mov eax,temp_float
	mov temp5,eax
	mov par_fun_hola_f, temp5
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
	mov temp8,eax
	mov eax,6
	imul eax,42
	mov temp9,eax
	mov eax,1
	add eax,temp9
	mov temp7,eax
	mov eax,temp7
	imul eax,temp8
	mov temp6,eax
	mov eax,temp6
	idiv 5
	mov temp6,eax
	mov var_i, temp6
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
	mov temp10,eax
	mov var_i, temp10
	jmp Label-IF-ELSE14
Label-IF-ELSE18:
	mov eax,8
	add eax,9
	mov temp10,eax
	mov var_x, temp10
Label-IF-ELSE14:
	mov var_i, 8
Label-IF-ELSE9:
	mov eax,8
	imul eax,9
	mov temp11,eax
	mov eax,temp11
	add eax,6
	mov temp10,eax
	mov eax,temp10
	imul eax,var_i
	mov temp10,eax
	mov var_i, temp10
