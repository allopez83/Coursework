#TODO: set up block mult - use given matrices at registers and increment to next row properly

# Terms:
# m - matrix
# r - row

# $t5 - matrix to print, current position in a matrix (getMatrix and printMatrix)
# $t6 - counter fro getMatrix
# $t7 - 1st counter for inner loop (printMatrix, getMatrix)
# $t8 - 2nd counter for outer loop (printMatrix, getMatrix)
# $t9 - Data position in matrix
# $t0-t8 - used by matrix multiply
# $t8 - 1st matrix to multiply (block mult)
# $t9 - 2nd matrix to multiply (block mult)

# $s0 - Reserved for matrix multiply
# $s1 - Reserved for matrix multiply
# $s2 - Reserved for matrix multiply

# $s4 - dimension of matrix (8)
# $s5 - start of m1
# $s6 - start of m2
# $s7 - start of m3 (results)

####################
# Start of program #
####################
# Save matrix information
li    $s4, 8        # Dimension of matrix
la    $s5, matrix   # Start of m1
la    $t5, matrix   # Position in matrix

# Create firs-t matrix
jal   getMatrix
# Save address of m2
move  $s6, $t5

# Create second matrix
jal   getMatrix
# Save address m3, result matrix
move $s7, $t5

# Matrix multiply
jal   matrixMult
# Print results
move  $t5, $s5      # Original matrix
jal   printMatrix
li    $v0, 4        # Results title
la    $a0, result
syscall
move  $t5, $s7      # Result matrix
jal   printMatrix

j     end

##############
# Procedures #
##############
##############
# matrixMult #
# non-block  #
##############
matrixMult:   # Matrix Multiplication Code
	# Reset temporary values
	sub   $t0, $t0, $t0
	sub   $t1, $t1, $t1
	sub   $t2, $t2, $t2
	sub   $t3, $t3, $t3
	sub   $t4, $t4, $t4
	sub   $t5, $t5, $t5
	sub   $t6, $t6, $t6
	sub   $t7, $t7, $t7
	sub   $t8, $t8, $t8
	sub   $t9, $t9, $t9
	# Load values required for matrixMult
	move  $t3, $s7         # $t3 will point to elements in result matrix
	move  $t4, $s4         # Matrix dimension
	li    $s0, 0           # 1st loop layer - Y axis of matrix 1 and result
L1:	li    $s1, 0           # 2nd loop layer - X axis of matrix 2 and result
L2:	li    $s2, 0           # 3rd loop layer - X axis of matrix 1 and Y axis of matrix 2 - Calculation offset
	# Data loading for matrix 2
L3:    mul   $t0, $s2, $s4    # Find the appropriate row by multiplying offset by matrix size
	addu  $t0, $t0, $s1    # Find appropriate column by adding offset
	sll   $t0, $t0, 2      # Turn into byte by multiplying 2^2, or 4
	addu  $t0, $t0, $s6    # Add base address of matrix 2
	lw    $t0, ($t0)       # Load value in matrix 2 from memory
	# Data loading for matrix 1
	mul   $t1, $s0, $s4    # Find the appropriate row by multiplying offset by matrix size
	addu  $t1, $t1, $s2    # Find appropriate column by adding offset
	sll   $t1, $t1, 2      # Turn into byte by multiplying 2^2, or 4
	addu  $t1, $t1, $s5    # Add base address of matrix 1
	lw    $t1, ($t1)       # Retrieve the value in matrix 1
	# Computational operations
	mul   $t0, $t0, $t1    # Multiply
	add   $t2, $t2, $t0    # Add result to matrix element
	sub   $t0, $t0, $t0    # Clean content of $t0 and $t1
	sub   $t1, $t1, $t1
	addiu $s2, $s2, 1      # Increment
	# Branch and store operations
	bne   $s2, $t4, L3
	sw    $t2, 0($t3)      # Store calculated data in $t2 into matrix 3, tracked by $t3
	addi  $t3, $t3, 4      # Increment counter $t3 by 4 to next matrix element
	sub   $t2, $t2, $t2    # Clean content of $t2
	addiu $s1, $s1, 1      # Increment and branch as necessary
	bne   $s1, $t4, L2
	addiu $s0, $s0, 1      # Increment and branch as necessary
	bne   $s0, $t4, L1
	
	jr    $ra              # Mission accomplished

##############
# matrixMult #
# block      #
##############
blockMult:    # Matrix Multiplication Code
	# Reset temporary values
	sub   $t0, $t0, $t0
	sub   $t1, $t1, $t1
	sub   $t2, $t2, $t2
	sub   $t3, $t3, $t3
	sub   $t4, $t4, $t4
	sub   $t5, $t5, $t5
	sub   $t6, $t6, $t6
	sub   $t7, $t7, $t7
	# Load values required for matrixMult
	move  $t3, $s7         # $t3 will point to elements in result matrix
	move  $t4, 4           # Half of matrix dimension, needs to be changed whenever size changes
	li    $s0, 0           # 1st loop layer - Y axis of matrix 1
L1:	li    $s1, 0           # 2nd loop layer - X axis of matrix 2
L2:	li    $s2, 0           # 3rd loop layer - X axis of matrix 1 and Y axis of matrix 2 - Calculation offset
	# Data loading for matrix 2
L3:    mul   $t0, $s2, $s4    # Find the appropriate row by multiplying offset by matrix size
	addu  $t0, $t0, $s1    # Find appropriate column by adding offset
	sll   $t0, $t0, 2      # Turn into byte by multiplying 2^2, or 4
	addu  $t0, $t0, $t8    # Add base address of matrix 2
	lw    $t0, ($t0)       # Load value in matrix 2 from memory
	# Data loading for matrix 1
	mul   $t1, $s0, $s4    # Find the appropriate row by multiplying offset by matrix size
	addu  $t1, $t1, $s2    # Find appropriate column by adding offset
	sll   $t1, $t1, 2      # Turn into byte by multiplying 2^2, or 4
	addu  $t1, $t1, $t9    # Add base address of matrix 1
	lw    $t1, ($t1)       # Retrieve the value in matrix 1
	# Computational operations
	mul   $t0, $t0, $t1    # Multiply
	add   $t2, $t2, $t0    # Add result to matrix element
	sub   $t0, $t0, $t0    # Clean content of $t0 and $t1
	sub   $t1, $t1, $t1
	addiu $s2, $s2, 1      # Increment
	# Branch and store operations
	bne   $s2, $t4, L3
	sw    $t2, 0($t3)      # Store calculated data in $t2 into matrix 3, tracked by $t3
	addi  $t3, $t3, 4      # Increment counter $t3 by 4 to next matrix element
	sub   $t2, $t2, $t2    # Clean content of $t2
	addiu $s1, $s1, 1      # Increment and branch as necessary
	bne   $s1, $t4, L2
	addiu $s0, $s0, 1      # Increment and branch as necessary
	bne   $s0, $t4, L1
	
	jr    $ra              # Mission accomplished

#############
# getMatrix #
#############
getMatrix:    # Automatically create a square matrix using memory location at $t5 incrementing at each row
	li    $t6, 1           # Give outer loop counter the matrix dimension
	move  $t8, $s4
getRowOuter:  # Create a row each pass
	move  $t7, $s4         # Give inner loop counter the matrix dimension
getRowInner:  # Create an element each pass
	# Store value into matrix element
	sw    $t6, ($t5)       # Store to element
	# Increment
	addi  $t5, $t5, 4      # Go to next memory location
	addi  $t7, $t7, -1     # Decrement counter for inner loop
	addi  $t6, $t6, 1      # Increment value counter
	# Looping instructions
	bgtz  $t7, getRowInner # Inner loop branch
	addi  $t8, $t8, -1     # Decrement counter for outer loop
	bgtz  $t8, getRowOuter # Outer loop branch
	jr    $ra              # Return to caller
	
###############
# printMatrix #
###############
printMatrix:  # Print out the matrix with starting point stored in $t5
	li    $v0, 4           # New line for formatting
	la    $a0, newLine
	syscall
	move  $t8, $s4         # Give outer loop counter the matrix dimension
printOuter:   # Print a row each pass
	move  $t7, $s4         # Give inner loop counter the matrix dimension
printInner:   # Print an element each pass
	# Action
	li    $v0, 1           # Print matrix element
	lw    $a0, 0($t5)
	syscall
	li    $v0, 4           # Divider for neatness
	la    $a0, divider
	syscall
	addi  $t5, $t5, 4      # Go to next memory location
	addi  $t7, $t7, -1     # Decrement counter for inner loop
	# Looping instructions
	bgtz  $t7, printInner  # Inner loop branch
	li    $v0, 4           # New line since this matrix row ends here
	la    $a0, newLine
	syscall
	addi  $t8, $t8, -1     # Decrement counter for outer loop
	bgtz  $t8, printOuter  # Outer loop branch
	jr    $ra              # Return to caller

	
j end               # Avoid junk code in case program falls through

##################
# End of program #
##################
end:

############################
# Variables and other data #
############################
.data

result:       .asciiz "\nMatrix multiplication result:\n"

divider:      .asciiz ",  "
newLine:      .asciiz "\n"

matrix:      .word   1 # Starting point of the matrice
matrix1:     .word   