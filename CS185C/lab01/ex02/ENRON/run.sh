#!/bin/bash
# Build and runs in one command!

application_code=application_1475566301268
assignment_dir=$HOME/185c/lab01/ex02/ENRON

workspaceSync () {
    printf "\n========== Updating ==========\n"
    rsync -rciv --progress $assignment_dir /user/mapr/
}
compile () {
    printf "\n========== Compiling ==========\n"
    ./rebuild.sh
}
run () {
    printf "\n========== Running ==========\n"
    ./rerun.sh
}
output () {
    printf "\n========== Output ==========\n"
    cat /user/mapr/ENRON/OUT/part-m-00000
}

# Regular run
if [[ "$1" == "" ]]; then
    workspaceSync
    compile
    run
    output
# Compile
elif [[ "$1" == "-c" ]]; then
    workspaceSync
    compile
# Update
elif [[ "$1" == "-u" ]]; then
    workspaceSync
# List
elif [[ "$1" == "-l" ]]; then
    yarn application -list
# Kill
elif [[ "$1" == "-k" ]]; then
    for a in $(seq $2 $3); do
        # Dirty way of dealing with single/double numbers
        yarn application -kill ${application_code}"_00"$a
        yarn application -kill ${application_code}"_000"$a
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
