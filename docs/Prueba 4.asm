section .data
	temp_float dd 0
	par_fun_hola_x dd 0
	par_fun_hola_r db 0
	par_fun_hola_f dd 0
	temp1 dw 0
	temp2 dw 0
	var_x dw 0
section .text
	global _start
hola:
	fld 6
	fadd 4
	fstp temp_float
	mov eax,temp_float
	mov temp2,eax
	fld temp2
	fmul par_fun_hola_f
	fstp temp_float
	mov eax,temp_float
	mov temp1,eax
	fld temp1
	fdiv 2.0
	fstp temp_float
	mov eax,temp_float
	mov temp1,eax
	mov par_fun_hola_x, temp1
	ret
_start:
	mov var_x, 20
