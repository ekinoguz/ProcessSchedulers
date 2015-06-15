import os

"""
Parser to get grades from all the *.run files under runs/ directory.
"""

directory = 'runs'
count = 0
for item in os.listdir(directory):
  if not item.endswith('run'):
    continue

  count += 1
  openfile = open(directory + '/' + item, 'r');
  lines = openfile.readlines();
  openfile.close()

  i = len(lines) - 1
  for i in range(len(lines) - 1, len(lines) - 10, -1):
    if i < 0:
      break
    line = lines[i].strip()
    if line.startswith(item[:-4]):
      print line
      break
print 'total of', str(count)