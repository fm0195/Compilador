section .data
	temp_float dd 0
	temp1 dw 0
	var_i dw 0
section .text
	global _start
_start:
	mov eax,4
	add eax,1
	mov temp1,eax
	mov var_i, temp1
