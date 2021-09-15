"""
DSC 20 Homework 06
"""


# Question 1
def count_substr(s, substr):
    """
    Takes a string (s) and a substring (substr), and counts how many times the
    substring appears in the string (case-sensitive). If substr is empty
    then returns len(s) + 1

    Params
    -----------------------------------
    s : string
        string that is searched
    substr : string
        substring to be counted in s

    Returns
    -----------------------------------
    int
        returns number of instances of which substr is found in s

    >>> count_substr('hello', '')
    6
    >>> count_substr('pen pineapple apple pen', 'apple')
    2
    >>> count_substr('oo ooo oooo ooooo oooooo', 'ooo')
    5
    >>> count_substr('leBron lebron LeBron ffdsBrondfs sdfsf', 'Bron')
    3
    >>> count_substr('sdf shdf kshdf kjsdf ksd', 'hd')
    2
    >>> count_substr('henlohellohenlofhejjhenlo', 'henlo')
    3
    """
    if substr == '':
        return len(s) + 1
    if len(s) == 0:
        return 0
    if s[:len(substr)] == substr:
        return 1 + count_substr(s[len(substr):], substr)
    return count_substr(s[1:], substr)


# Question 2
def encode(mapping, plaintext):
    """
    Encodes a given piece of text (plaintext) with a mapping dictionary.
    Dictionary has characters (strings of length 1) as keys, and strings of
    length 3 as values. Looks up each character of the
    plaintext in the mapping dictionary, and replace this character
    with the corresponding 3-character pattern.

    Params
    -----------------------------------
    mapping : dictionary
        dictionary containing mapping instructions
    plaintext : string
        text to be encoded using dictionary

    Returns
    -----------------------------------
    string
        returns encoded string

    >>> mapping = {'z': 'not', 'r': 'ece', 't': 'dsc'}
    >>> encode(mapping, 'zzr')
    'notnotece'
    >>> encode(mapping, 'zzt')
    'notnotdsc'
    >>> encode(mapping, 'ttzrr')
    'dscdscnoteceece'
    >>> encode(mapping, 'ttzrtzrtzrt')
    'dscdscnotecedscnotecedscnotecedsc'
    >>> encode(mapping, 'zzztzrzzzrrt')
    'notnotnotdscnotecenotnotnoteceecedsc'
    >>> encode(mapping, 'zrtrztzztrz')
    'notecedscecenotdscnotnotdscecenot'
    """
    if len(plaintext) == 0:
        return ''
    if plaintext[0] in mapping.keys():
        return mapping[plaintext[0]] + encode(mapping, plaintext[1:])
    return encode(mapping, plaintext[1:])


# Question 3
def foo(num):
    """
    If number > 1 recursively call bar(num + 2) or else return 0

    Params
    -----------------------------------
    num : int
        Starting integer for teh sequence

    Returns
    -----------------------------------
    int
        returns integer +2 if num is even +1 if num is odd

    >>> foo(0)
    0
    >>> foo(3)
    1
    >>> foo(10)
    6
    >>> foo(64)
    33
    >>> foo(35)
    17
    >>> foo(23)
    11
    """
    incr = 2
    if num <= 1:
        return 0
    else:
        return bar(num + incr)


def bar(num):
    """
    If number is even then recursively call bar, decrement num by 1 return +2
    else recursively call foo, decrement num by 2 return +1

    Params
    -----------------------------------
    num : int
        Integer in the sequence

    Returns
    -----------------------------------
    int
        returns integer +2 if num is even +1 if num is odd

    >>> bar(2)
    3
    >>> bar(11)
    4
    >>> bar(100)
    50
    >>> bar(34)
    17
    >>> bar(33)
    15
    >>> bar(52)
    26
    """
    incr1 = 1
    incr2 = 2
    decr = 4
    if num % 2 == 0:
        return bar(num - incr1) + incr2
    else:
        return foo(num - decr) + incr1


# Question 4
def add_all_digits(num):
    """
    Given a non-negative integer num, this function repeatedly adds all digits
    until the result becomes a single-digit number.

    Params
    -----------------------------------
    num : int
        The integer to be added

    Returns
    -----------------------------------
    int
        returns single digit number

    >>> add_all_digits(41)
    5
    >>> add_all_digits(567)
    9
    >>> add_all_digits(999777)
    3
    >>> add_all_digits(213432)
    6
    >>> add_all_digits(547832)
    2
    >>> add_all_digits(5)
    5
    """
    if len(str(num)) == 1:
        return num
    return add_all_digits(sum([int(i) for i in str(num)]))


# Question 5
def find_min_recursive(lst):
    """
    Function that returns the smallest element in the input list.

    Params
    -----------------------------------
    num : int
        The integer to be added

    Returns
    -----------------------------------
    int
        returns single digit number

    >>> find_min_recursive([1, 2, 3, 4, 5])
    1
    >>> find_min_recursive([10, 11, 5, 0, -10, 1])
    -10
    >>> find_min_recursive(['b', 'c', 'z', 'y', 'a', 'e'])
    'a'
    >>> find_min_recursive([-3, -5, 10, 40, 20])
    -5
    >>> find_min_recursive(['sdfasdf', 'asdfasdf', 'asdf', 'dghre'])
    'asdf'
    >>> find_min_recursive([True, False, True, False, False])
    False
    """
    max_len = 2
    if len(lst) < max_len:
        return lst[0]
    if lst[0] < lst[1]:
        lst.remove(lst[1])
        return find_min_recursive(lst)
    else:
        lst.remove(lst[0])
        return find_min_recursive(lst)


# Question 6
def skip_then_swap(string, n_skip, n_swap):
    """
    Function that takes a string, skips the first n_skip character pairs,
    then swaps the next n_swap character pairs.

    Params
    -----------------------------------
    string : string
        String to be manipulated
    n_skip : string
        number of ith pairs to skip
    n_swap : string
        The integer to be added

    Returns
    -----------------------------------
    int
        returns single digit number

    >>> skip_then_swap('kkkABXXXXCDkkk', 3, 2)
    'kkkDCXXXXBAkkk'
    >>> skip_then_swap('DSC20', 1, 2)
    'D2CS0'
    >>> skip_then_swap('skip_then_swap', 4, 3)
    'skip_neht_swap'
    >>> skip_then_swap('bonks', 2, 1)
    'bonks'
    >>> skip_then_swap('Racecar', 0, 1)
    'racecaR'
    >>> skip_then_swap('Nikola Jokic', 2, 3)
    'NikoJa lokic'
    """
    if len(string) == 1:
        return string
    if len(string) < 1:
        return ''
    if n_skip > 0:
        return string[0] + skip_then_swap(string[1:-1], n_skip - 1, n_swap) + \
               string[-1]
    if n_swap > 0:
        return string[-1] + skip_then_swap(string[1:-1], n_skip, n_swap - 1) + \
               string[0]
    return string[0] + skip_then_swap(string[1:-1], n_skip, n_swap) + string[-1]


# Question 7
def flatten_dict(nested_dict):
    """
    Function that flattens a nested dictionary where the key of a value is a
    concatenation of all keys you would use to trace down to that value in the
    original nested dictionary.

     Params
    -----------------------------------
    nested_dict : dict
        dictionary to be flattened

    Returns
    -----------------------------------
    dictionary
        returns a flattened form of the given dictionary

    >>> flatten_dict({'A': 1, 'B': 2})
    {'A': 1, 'B': 2}
    >>> flatten_dict({'Hi': True, 'Hello': {'World': 'Java',
    ... 'Kitty': 'Python'}})
    {'Hi': True, 'HelloWorld': 'Java', 'HelloKitty': 'Python'}
    >>> flatten_dict({'A': {'B': 1, 'C': 2, 'D': {'E': 3, 'F': 4}},
    ... 'G': 5, 'H': 6})
    {'AB': 1, 'AC': 2, 'ADE': 3, 'ADF': 4, 'G': 5, 'H': 6}
    >>> flatten_dict({'J' : {'A' : 2, 'M' : 3}, 'E' : 4, 'S' : 5})
    {'JA': 2, 'JM': 3, 'E': 4, 'S': 5}
    >>> flatten_dict({'N' : {'I' : 2, 'K' : 3}, 'O' : 4, 'L' : 5, 'A' : \
{'J': 6, 'O' : 7}})
    {'NI': 2, 'NK': 3, 'O': 4, 'L': 5, 'AJ': 6, 'AO': 7}
    >>> flatten_dict({'N' : {'J' : 2, 'K' : {'G' : 8, 'I': 9, 'C': 10}}, 'O' :\
    4, 'L' : 5, 'A' : {'J': 6, 'O' : 7}})
    {'NJ': 2, 'NKG': 8, 'NKI': 9, 'NKC': 10, 'O': 4, 'L': 5, 'AJ': 6, 'AO': 7}
    """
    if all([not isinstance(i, dict) for i in nested_dict.values()]):
        return nested_dict
    new_dict = {}
    rcrsn = flatten_dict
    for k, v in nested_dict.items():
        if isinstance(v, dict):
            j = rcrsn(v)
            for i, j in j.items():
                new_dict[k + i] = j
        else:
            new_dict[k] = v
    return new_dict
