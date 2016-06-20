section .data
	var_fun_p dd 0
	var_fun_q db 0
	var_fun_wq db 0
	var_fun_z dd 0
	var_fun_m db 0
	var_fun_n db 0
	var_fun_o db 0
	temp1 dw 0
	temp2 dw 0
	temp3 dw 0
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
	temp4 dw 0
	temp5 dw 0
	temp6 dw 0
	temp7 dw 0
	temp8 dw 0
	temp9 dw 0
section .text
	global _start
hola:
	mov eax,63
	idiv 8
	mov temp2,eax
	mov eax,2
	add eax,9
	mov temp3,eax
	mov eax,temp3
	add eax,8
	mov temp1,eax
	mov eax,temp1
	add eax,temp2
	mov temp1,eax
	mov null, temp1
	mov eax,3
	add eax,null
	mov temp4,eax
	mov null, temp4
ret
_start:
	mov var_x, 0
	mov var_i, 0
	mov eax,3
	add eax,4
	mov temp6,eax
	mov eax,6
	imul eax,42
	mov temp7,eax
	mov eax,1
	add eax,temp7
	mov temp5,eax
	mov eax,temp5
	imul eax,temp6
	mov temp4,eax
	mov eax,temp4
	idiv 5
	mov temp4,eax
	mov var_i, temp4
	mov eax,8
	imul eax,9
	mov temp9,eax
	mov eax,temp9
	add eax,6
	mov temp8,eax
	mov eax,temp8
	imul eax,var_i
	mov temp8,eax
	mov var_i, temp8
