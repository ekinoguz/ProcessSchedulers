make clean;
make;
java Tester;

declare -i grade=0

# Add your test comparisons to below

# FCFS
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


# SRTF
if diff -q tests/srtf1.output tests/srtf1.expected > /dev/null; then
  : # files are the same
  echo 'passing 3'
  grade=$((grade + 10))
else
  : # files are different
  echo 'failing 3'
fi

echo 'Grade is:' $grade