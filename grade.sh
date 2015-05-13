#!/bin/sh

if [ -z "$1" ]
  then
    echo 'Must enter a parameter <student-id-number>';
    exit;
fi

SUBMISSION="$1"'.zip'
HOME="$1"'/'

echo 'Creating a directory for' $HOME;
mkdir $HOME;

echo 'Unzipping the submission' $SUBMISSION;
cp $SUBMISSION $HOME;
cd $HOME;
unzip -q $SUBMISSION;

echo 'Copying private tests and Tester.java into' $HOME;
cd '../';
cp Tester.java $HOME;
cp -r tests $HOME;
cd $HOME;

echo 'Compiling the submission'
make clean;
make;
java Tester;

# Declare grade variables
grade=0;
bonus=0;

# Add your test comparisons to below

# FCFS
echo 'Testing FCFS...'
if diff -q tests/fcfs1.output tests/fcfs1.expected > /dev/null; then
  : # files are the same
  echo 'passing 1'
  grade=$((grade + 10))
else
  : # files are different
  echo 'failing 1'
fi

if diff -q tests/fcfs2.output tests/fcfs2.expected > /dev/null; then
  : # files are the same
  echo 'passing 2'
  grade=$((grade + 10))
else
  : # files are different
  echo 'failing 2'
fi

if diff -q tests/fcfs3.output tests/fcfs3.expected > /dev/null; then
  : # files are the same
  echo 'passing 3'
  grade=$((grade + 10))
else
  : # files are different
  echo 'failing 3'
fi

if diff -q tests/fcfs4.output tests/fcfs4.expected > /dev/null; then
  : # files are the same
  echo 'passing 4'
  grade=$((grade + 10))
else
  : # files are different
  echo 'failing 4'
fi

# SRTF
echo 'Testing SRTF...'
if diff -q tests/srtf1.output tests/srtf1.expected > /dev/null; then
  : # files are the same
  echo 'passing 1'
  grade=$((grade + 10))
else
  : # files are different
  echo 'failing 1'
fi

if diff -q tests/srtf2.output tests/srtf2.expected > /dev/null; then
  : # files are the same
  echo 'passing 2'
  grade=$((grade + 10))
else
  : # files are different
  echo 'failing 2'
fi

if diff -q tests/srtf3.output tests/srtf3.expected > /dev/null; then
  : # files are the same
  echo 'passing 3'
  grade=$((grade + 10))
else
  : # files are different
  echo 'failing 3'
fi

if diff -q tests/srtf4.output tests/srtf4.expected > /dev/null; then
  : # files are the same
  echo 'passing 4'
  grade=$((grade + 10))
else
  : # files are different
  echo 'failing 4'
fi

if diff -q tests/srtf5.output tests/srtf5.expected > /dev/null; then
  : # files are the same
  echo 'passing 5'
  grade=$((grade + 10))
else
  : # files are different
  echo 'failing 5'
fi

# PS
echo 'Testing PS...'
if diff tests/ps1.output tests/ps1.expected > /dev/null; then
  : # files are the same
  echo 'passing 1'
  bonus=$((bonus + 10))
else
  : # files are different
  echo 'failing 1'
fi

if diff -q tests/ps2.output tests/ps2.expected > /dev/null; then
  : # files are the same
  echo 'passing 2'
  bonus=$((bonus + 10))
else
  : # files are different
  echo 'failing 2'
fi

if diff -q tests/ps3.output tests/ps3.expected > /dev/null; then
  : # files are the same
  echo 'passing 3'
  bonus=$((bonus + 10))
else
  : # files are different
  echo 'failing 3'
fi

if diff -q tests/ps4.output tests/ps4.expected > /dev/null; then
  : # files are the same
  echo 'passing 4'
  bonus=$((bonus + 10))
else
  : # files are different
  echo 'failing 4'
fi

if diff -q tests/ps5.output tests/ps5.expected > /dev/null; then
  : # files are the same
  echo 'passing 5'
  bonus=$((bonus + 10))
else
  : # files are different
  echo 'failing 5'
fi

echo 'Grade is:' $grade;
echo 'Bonus grade is:' $bonus;