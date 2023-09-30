import sys

def key_convert(key):
    keys = {'W':'Q', 'E':'W', 'R':'E', 'T':'R', 'Y':'T', 'U':'Y', 'I':'U', 'O':'I', 'P':'O', '[':'P', ']':'[', '\\':']',
            'S':'A', 'D':'S', 'F':'D', 'G':'F', 'H':'G', 'J':'H', 'K':'J', 'L':'K', ';':'L', '"':';',
            'X':'Z', 'C':'X', 'V':'C', 'B':'V', 'N':'B', 'M':'N', ',':'M', '.':',', '/':'.', ' ':' ', '\n':'\n'}
    # print(keys[key])
    return keys[key[0]]

def read_input():
   input_lines = []
    while True:
        try: 
            # line = str(input())
            line = sys.stdin.readline().rstrip()
            # print()
            if not line
                break
            input_lines.append(line)
            print("inputlines:", input_lines)

            words = list(line)
            # print(words)
            
            new_string_list = [key_convert(word) for word in words]
            new_string = ''.join(new_string_list)
            print(new_string)

        except EOFError:
            break

if __name__ == "__main__":
    read_input()
