section .data
	var_i dw 0
	temp1 dw 0
	temp2 dw 0
	temp3 dw 0
	temp4 dw 0
section .text
	global _start
_start:
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
