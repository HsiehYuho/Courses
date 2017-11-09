# python3

import sys

class Bracket:
    def __init__(self, bracket_type, position):
        self.bracket_type = bracket_type
        self.position = position

    def Match(self, c):
        if self.bracket_type == '[' and c == ']':
            return True
        if self.bracket_type == '{' and c == '}':
            return True
        if self.bracket_type == '(' and c == ')':
            return True
        return False

if __name__ == "__main__":
    text = sys.stdin.read()
    opening_brackets_stack = []
    error = 0
    for i, next in enumerate(text):
        if next == '(' or next == '[' or next == '{':
            # Process opening bracket, write your code here
            bracketItem = Bracket(next,i)
            opening_brackets_stack.append(bracketItem)
            
        if next == ')' or next == ']' or next == '}':
            # Process closing bracket, write your code here
            if len(opening_brackets_stack) == 0:
                error = i+1
                break 
            else:  
                bracketItem = opening_brackets_stack.pop()
                if (bracketItem.Match(next)) == False:
                    error = i+1
                    break
    if error == 0 and len(opening_brackets_stack) == 0:
        print("Success") 
    elif error != 0:
        print (error)
    else:
        print (opening_brackets_stack.pop(0).position+1)
    # Printing answer, write your code here
