# Name
li $v0, 4 # Print prompt
la $a0, namePrompt_string
syscall
li $v0, 8 # Get user string input
la $a0, nameInput_string
lw $a1, inputLength
syscall

# Age
li $v0, 4 # Print prompt
la $a0, agePrompt_string
syscall
li $v0, 5 # Get user integer input
syscall
move $t0, $v0
addi $t0, $t0, 4 # Process new age by adding 4

# Print user info
li $v0, 4 # Hello
la $a0, nameResponse_string
syscall
li $v0, 4 # Name
la $a0, nameInput_string
syscall
li $v0, 4 #  You'll be
la $a0, ageResponse_front_string
syscall
li $v0, 1 # Age
move $a0, $t0
syscall
li $v0, 4 # years old
la $a0, ageResponse_back_string
syscall

.data
namePrompt_string: .asciiz "What is your name? "
agePrompt_string: .asciiz "What is your age? "
nameResponse_string: .asciiz "Hello, "
ageResponse_front_string: .asciiz "You will be "
ageResponse_back_string: .asciiz " years old in four years"

nameInput_string: .space 51
inputLength: .word 50