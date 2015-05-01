#Yes! got the stack working!

# $s0 - start of stack
move $s0, $sp

# Print title
li $v0, 4
la $a0, row1
syscall

# Get number
li $v0, 5
syscall
sw $v0, 0($sp)
addi $sp, $sp, 4

li $v0, 5
syscall
sw $v0, 0($sp)
addi $sp, $sp, 4

li $v0, 5
syscall
sw $v0, 0($sp)
addi $sp, $sp, 4

# Print out value of stack pointer at 0
move $sp, $s0

li $v0, 1
lw $a0, 0($sp)
syscall

addi $sp, $sp, 4
li $v0, 1
lw $a0, 0($sp)
syscall

addi $sp, $sp, 4
li $v0, 1
lw $a0, 0($sp)
syscall

j end


############
# Old Code #
############
addiu $t0, $t0, 3
addiu $t1, $t1, 5
mul   $s0, $t0, $t1

li    $v0, 1 # print an integer
move  $a0, $s0 # integer to print goes in a0
syscall

sll $s1, $s0, 1
srl $s2, $s0, 1

move  $a0, $s1 # integer to print goes in a0
syscall
move  $a0, $s2 # integer to print goes in a0
syscall


end:

.data
row1: .asciiz "\nFirst row:\n"