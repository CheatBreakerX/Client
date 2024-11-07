# hi from force build - 7/11/24, Lifix
from pathlib import Path
import re

correct = 0
incorrect = 0
result = list(Path("./versions").rglob("Mixin*.[jJ][aA][vV][aA]"))
for path in result:
	lines = open(path, 'r').readlines()
	for line in lines:
		if "@Mixin" in line.strip():
			path_string = str(path)
			mixin_name = re.split('\(', line.strip())[1].replace(".class)", "")
			mixin_file_name = path_string.split('\\')[len(path_string.split('\\')) - 1]
			correct_file_name = "Mixin" + mixin_name + ".java"

			if mixin_file_name.__eq__(correct_file_name):
				print(f"Correct: {path}")
				correct += 1
			else:
				print(f"Incorrect: {path} (proper name: {correct_file_name})")
				incorrect += 1

total = correct + incorrect
tense_has_have = "have"
if incorrect == 1:
	tense_has_have = "has"

print(f"Finished. {correct}/{total} Mixin names are formatted properly, {incorrect} {tense_has_have} been found as incorrect.")
