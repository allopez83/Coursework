	.text
	.globl main
main:
	subu  $sp,$sp,32
	sw    $ra,20($sp)
	sw    $fp,16($sp)
	addiu $fp,$sp,28
	
	li    $a0,10
	jal   fact
	
	la    $a0,$LC
	move  $a0,$v0
	# jal   printf # Broken
	
	li $v0, 1 # Using syscall instead
	syscall
	
	j End # Exit
	
	.data
$LC:
	.ascii "The factorial of 10 is %d\n\000"
	
	.text
fact:
	subu  $sp,$sp,32
	sw    $ra,20($sp)
	sw    $fp,16($sp)
	addiu $fp,$sp,28
	sw    $a0,0($fp)
	
	lw    $v0,0($fp)
	bgtz  $v0,$L2
	li    $v0,1
	j      $L1
	
$L2:
	lw    $v1,0($fp)
	subu  $v0,$v1,1
	move  $a0,$v0
	jal   fact
	
	lw    $v1,0($fp)
	mul   $v0,$v0,$v1
	
$L1:
	lw    $ra,20($sp)
	lw    $fp,16($sp)
	addiu $sp,$sp,32
	jr    $ra
	
End:
