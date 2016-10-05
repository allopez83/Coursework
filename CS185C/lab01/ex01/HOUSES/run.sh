#!/bin/bash
# Build and runs in one command!

# Regular run
if [[ "$1" == "" ]]; then
    printf "\n========== Updating ==========\n"
    rsync -rciv --progress $HOME/185c/lab01/ex01/HOUSES /user/mapr/
    printf "\n========== Compiling ==========\n"
    ./rebuild.sh
    printf "\n========== Running ==========\n"
    ./rerun.sh
    printf "\n========== Output ==========\n"
    cat /user/mapr/HOUSES/OUT/part-r-00000
# Compile
elif [[ "$1" == "-c" ]]; then
    printf "\n========== Updating ==========\n"
    rsync -rciv --progress $HOME/185c/lab01/ex01/HOUSES /user/mapr/
    printf "\n========== Compiling ==========\n"
    ./rebuild.sh
# Update
elif [[ "$1" == "-u" ]]; then
    rsync -rciv --progress $HOME/185c/lab01/ex01/HOUSES /user/mapr/
# List
elif [[ "$1" == "-l" ]]; then
    yarn application -list
# Kill
elif [[ "$1" == "-k" ]]; then
    for a in $(seq $2 $3); do
        # echo "application_1475206066057_00$a"
        yarn application -kill application_1475391051261_00$a
    done
# Test
elif [[ "$1" == "-t" ]]; then
    hadoop jar /opt/mapr/hadoop/hadoop-2.7.0/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.0-mapr-1602.jar wordcount file:///etc/passwd /tmp/out-$USER
    hadoop fs -cat /tmp/out-$USER/part-r-00000
# Mount sshfs
elif [[ "$1" == "-m" ]]; then
    sudo sshfs -o allow_other,transform_symlinks,follow_symlinks rayting@172.16.80.1:/home/rayting/185c ~/185c
# None
else
    echo "option '$1' not recognized."
fi
