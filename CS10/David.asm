# David Reinke Lab3 3x3 matrix multiplication
#
# void mm ( int c[][], int a[][], int b[][]) {
#    int i, j, k;
#    for (i=0, i!=4, i=i+1)
#        for (j=0, j!=4, j=j+1)
#            for(k=0, k!=4, k=k+1)
#                c[i][j] = c[i][j] + a[i][k] + b[k][j];
# }

.text
main:
    li     $s4, 4        # save matrix size for use later
    la     $a1, A        # array starting addresses in $a0, $a1, $a2
    la     $a2, B        # integer variables in $s0, $s1, $s2
    la     $a3, C
   
    jal     mm
    jal     printC
   
    li $v0, 10
    syscall

	 
.data
resultTitle: .asciiz "C= "
newLine:     .asciiz "\n"
comma:       .asciiz ", "
       
A: .word 1, 2, 3, 4
         5, 6, 7, 8
         9, 10, 11, 12
         13, 14, 15, 16
     
B: .word 16, 15, 14, 13
         12, 11, 10, 9
         8, 7, 6, 5
         4, 3, 2, 1
   
C: .word 0:16            # 16 * 4 bytes
str: .asciiz "C= "

.text

mm:
    li    $t1, 4        # $t1 = 4 (row size/loop end)
    li    $s0, 0        # i = 0; initialize 1st loop for loop
    li    $t9, 0
   
L1:    li    $s1, 0        # j = 0; restart 2nd for loop
L2:    li    $s2, 0        # k = 0; restart 3rd for loop

    sll    $t2, $s0, 2    # t2 = i * 2^2 (size of row c)
    addu    $t2, $t2, $s1    # $t2 = i * size(row) + j
    sll    $t2, $t2, 2    # $t2 = byte offset of [i][j]
    addu    $t2, $a3, $t2    # $t2 = byte address of c[i][j]
    li    $t4, 0
       
L3:    sll    $t0, $s2, 2    # $t0 = k * 2^4 (size of row b)
    addu     $t0, $t0, $s1    # $t0 = k * size(row) + j
    sll     $t0, $t0, 2    # $t0 = byte offset of [k][j]
    addu    $t0, $a2, $t0    # $t0 = byte address of b[k][j]
    lw    $t5, 0($t0)    # $s4 = 8 bytes of b[k][j]            $f16, 0($t0)
   
    sll    $t0, $s0, 2    # $t0 = i * 2^5 (size of row a)
    addu     $t0, $t0, $s2    # $t0 = k * size(row) + k
    sll     $t0, $t0, 2    # $t0 = byte offset of [i][k]
    addu    $t0, $a1, $t0    # $t0 = byte address of a[i][k]
    lw    $t6, 0($t0)    # $s5 = 8 bytes of a[i][k]            $f18, 0($t0)
   
    addi    $t9, $t9, 1
    mul    $t5, $t5, $t6    # $s5 = a[i][k] * b[k][j]            $f16, $f18, $f16
    add    $t4, $t4, $t5    # $s3 = c[i][j] + a[i][k] * b[k][j]          $f4, F4, $f16
   
    addiu    $s2, $s2, 1    # $k = k + 1
    bne    $s2, $t1, L3    # if (k != 3) go to L3
    sw    $t4, 0($t2)    # c[i][j] = $f4                    $f4, 0($t2)
   
    addiu    $s1, $s1, 1    # $j = j + 1
    bne    $s1, $t1, L2    # if (j != 4) go to L2
    addiu    $s0, $s0, 1    # $i = i + 1
    bne    $s0, $t1, L1    # if (i != 4) go to L1
    jr     $ra   

printC:    li    $v0, 4
    la    $a0, newLine
    syscall
    move    $t8, $s4
	 move    $t2, $a3    # need starting point of matrix c
printOuter:
    move    $t7, $s4     # outer loop needs dimension of matrix
printInner:
    li    $v0, 1
    lw    $a0, 0($t2)      # print matrix element
    syscall
    li    $v0, 4
    la    $a0, comma
    syscall
    addi    $t2, $t2, 4    # next element in C
    addi    $t7, $t7, -1    # decrement inner loop
   
    bgtz    $t7, printInner # if inner loop counter is greater than zero, loop again
    li    $v0, 4
    la    $a0, newLine
    syscall
    addi    $t8, $t8, -1    # decrement outer loop
    bgtz    $t8, printOuter # if outer loop counter is greater than zero, loop again
    jr    $ra
