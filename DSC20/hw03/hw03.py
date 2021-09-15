"""
DSC 20 Homework 03
"""


# Question 1
def assert_playground(num, lst, *args, **kwargs):
    """
    Returns num if num is positive integer, lst is a list containing only ints
    or floats with the sum being < 20, args are all floats with at least one
    being between [-5, 5], and kwarg values are all strings with length > 1 with
    the first and last character matching

    Params
    -----------------------------------
    num : int
        positive integer
    lst : list
        list of ints or float, sum of which must be < 20
    *args : float
        arbitrary number of floats with at least one of which falling in the
        interval [-5.0, 5.0]
    **kwargs : string
        values must be strings with length > 1 and the first and last character
        must match

    Returns
    -----------------------------------
    int
        returns num if all parameters are valid

    >>> assert_playground(15, [9.5, 1], -3.1, 9.2,
    ... s1="Jessie J", s2="racecar")
    15
    >>> assert_playground(2.0, [9.5, 1], -3.1, 9.2,
    ... s1="Jessie J", s2="racecar")
    Traceback (most recent call last):
    AssertionError
    >>> assert_playground(15, [4, 7.0, 9.1], -3.1, 9.2,
    ... s1="Jessie J", s2="racecar")
    Traceback (most recent call last):
    AssertionError
    >>> assert_playground(15, [9.5,7, 4, 1], 5.4, -3.1, 9.2, s1="Hokic",
    ... s2="Pokic")
    Traceback (most recent call last):
    AssertionError
    >>> assert_playground(78, [6.5,7, 4, 1], 5.4, -3.1, 9.2, s1="Jessie J",
    ... s2="YummY")
    78
    >>> assert_playground(-7, [9.5,7, 4, 1], 5.4, -3.1, 9.2, s1="Hokic",
    ... s2="Pokic")
    Traceback (most recent call last):
    AssertionError
    """
    max_sum_lst = 20
    interval = 5.0
    assert ((isinstance(num, int)) & (num > 0)
            & ((isinstance(lst, list)) & (all([isinstance(x, int) |
                                               isinstance(x, float) for x in
                                               lst])) & (
                       sum(lst) < max_sum_lst))
            & ((all([isinstance(x, float) for x in args])) & (
                any([(x >= -interval) &
                     (x <= interval) for x
                     in args])))
            & (all(
                [(isinstance(x, str) & (len(x) > 1) & (x[0] == x[-1])) for x in
                 kwargs.values()])))
    return num


# Question 2
def insert_spaces(lst):
    """
    Inserts spaces for each string in a list, if the string is empty 1 space is
    inserted, if the string is <= 4 characters then a space is placed between
    each character in the string, if the string is <= 8 characters then a space
    is placed between every two characters, else a space is placed between every
    3 characters

    Params
    -----------------------------------
    lst : list
        list of strings to be manipulated

    Returns
    -----------------------------------
    list
        returns list containing the modified strings

    >>> insert_spaces(["", "hola"])
    [' ', 'h o l a']
    >>> insert_spaces(["sean", "marina", "cumberbatch"])
    ['s e a n', 'ma ri na', 'cum ber bat ch']
    >>> insert_spaces(["I love Python!!"])
    ['I l ove  Py tho n!!']
    >>> insert_spaces(["I love Style Errors!!"])
    ['I l ove  St yle  Er ror s!!']
    >>> insert_spaces(["bong", 'bing', 'boink'])
    ['b o n g', 'b i n g', 'bo in k']
    >>> insert_spaces(["fsf!!", 'sdf', 'asdfasdf'])
    ['fs f! !', 's d f', 'as df as df']
    """
    len_every_two = 4
    every_two = 2
    every_three = 3
    len_every_three = 8
    assert ((isinstance(lst, list)) & all([isinstance(x, str) for x in lst]))
    return [' ' if len(x) == 0 else ' '.join(
        x[i:i + 1] for i in range(0, len(x), 1))
    if len(x) <= len_every_two else ' '.join(
        x[i:i + every_two] for i in range(0, len(x), every_two))
    if len(x) <= len_every_three else ' '.join(
        x[i:i + every_three] for i in range(0, len(x), every_three))
            for x in lst]


# Question 3
def find_greatest_divisor(lower, upper):
    """
    Returns the greatest divisor for each integer between two inclusive bounds

    Params
    -----------------------------------
    lower : int
        lower bound of the range
    upper : int
        upper bound of the range

    Returns
    -----------------------------------
    dict
        returns a dictionary where the keys are each integer in the range and
        the values are the greatest divisor of the integer

    >>> find_greatest_divisor(20, 27)
    {20: 5, 21: 7, 22: 2, 23: 1, 24: 8, 25: 5, 26: 2, 27: 9}
    >>> find_greatest_divisor(1, 10)
    {1: 1, 2: 2, 3: 3, 4: 4, 5: 5, 6: 6, 7: 7, 8: 8, 9: 9, 10: 5}
    >>> find_greatest_divisor(11, 19)
    {11: 1, 12: 6, 13: 1, 14: 7, 15: 5, 16: 8, 17: 1, 18: 9, 19: 1}
    >>> find_greatest_divisor(98, 25)
    Traceback (most recent call last):
    AssertionError
    >>> find_greatest_divisor(3, 12)
    {3: 3, 4: 4, 5: 5, 6: 6, 7: 7, 8: 8, 9: 9, 10: 5, 11: 1, 12: 6}
    >>> find_greatest_divisor(78, 84)
    {78: 6, 79: 1, 80: 8, 81: 9, 82: 2, 83: 1, 84: 7}
    >>> find_greatest_divisor(32, 37)
    {32: 8, 33: 3, 34: 2, 35: 7, 36: 9, 37: 1}
    """
    single_digit_requirement = 10
    assert (isinstance(lower, int) & isinstance(upper, int) & (lower <= upper))

    return {i: max([j for j in range(1, i + 1) if
                    (i % j == 0) & (j < single_digit_requirement)])
            for i in range(lower, upper + 1)}


# Question 4
def best_player(**player_scores):
    """
    Finds the best player from an arbitrary number of player scores, removes
    min and max score from each player and averages the remaining scores to
    find the highest scoring player

    Params
    -----------------------------------
    **player_scores : list
        arbitrary number of player scores where the value is a list containing
        all scores for that player

    Returns
    -----------------------------------
    str
        returns the name of the player with the highest average score

    >>> best_player(marina=[9.6, 9, 9.8, 9.9], darren=[9.0, 9.5, 9.9],
    ... elvy=[10.0, 9.8, 10.0, 9.5, 9.6])
    'elvy'
    >>> best_player(sean=[100, 99.99, 100])
    'sean'
    >>> best_player(james=[3.8, 3.5, 3.2], colin=[4.0, 3.6, 3.0])
    'colin'
    >>> best_player(james=[-3, 3.5, 3.2], colin=[4.0, 3.6, 3.0])
    Traceback (most recent call last):
    AssertionError
    >>> best_player(james=[5.7, 5.8, 9.7], colin=[4.5, 6.2, 5.2, 8.6])
    'james'
    >>> best_player(bing=[3.3, 8.8, 9.7], colin=[2.5, 6.2, 8.2, 9.6],
    ... bong=[5.7, 9.8, 9.7], boink=[4.5, 6.2, 5.2, 8.6])
    'bong'
    """
    assert (all([isinstance(i, list) for i in player_scores.values()] +
                [j > 0 for i in player_scores.values() for j in i]))
    return max([(player, sum(sorted(player_scores[player])[1:-1]) /
                 len(sorted(player_scores[player])[1:-1])) for player
                in player_scores], key=lambda i: i[1])[0]


# Question 5
def deserialize(outpath, patterns, *serialized_lines):
    """
    Takes a list of patterns, an arbitrary number of serialized lines, and
    a path, and deserializes the lines and writes them out to a new file

    Params
    -----------------------------------
    outpath : str
        path to which the file will be written to
    patterns : list
        list of patterns that are drawn
    *serialized_lines : list
        arbitrary number of "serialized" lists

    >>> deserialize("outfiles/out1.txt", ["**", "Marina"],
    ... [1,1,1], [0,5], [3,3,0,3,3])
    >>> with open("outfiles/out1.txt", "r") as outfile1:
    ...     print(outfile1.read().strip())
    **Marina**
    MarinaMarinaMarinaMarinaMarina
    ******MarinaMarinaMarinaMarinaMarinaMarina******

    >>> deserialize("outfiles/out2.txt", ["__", "()", "??"],
    ... [2,4,0,2], [1,2,0,2,2,0,1], [0,2,0,4,2,0], [0,1,0,6,1,0])
    >>> with open("outfiles/out2.txt", "r") as outfile2:
    ...     print(outfile2.read().strip())
    ____()()()()____
    __()()____()()__
    ()()________()()
    ()____________()

    >>> deserialize("outfiles/out3.txt", ["##", "__"],
    ... [2,3,2,2,2,1,2,3,1,1], [1,1,1,1,1,3,1,5,1,1,1,1,1],
    ... [1,1,1,2,1,2,1,4,1,2,1,1,1], [1,1,1,3,1,1,1,3,1,3,1,1,1],
    ... [2,2,2,3,2,1,3,2,1,1])
    >>> with open("outfiles/out3.txt", "r") as outfile3:
    ...     print(outfile3.read().strip())
    ####______####____####__####______##__
    ##__##__##______##__________##__##__##
    ##__##____##____##________##____##__##
    ##__##______##__##______##______##__##
    ####____####______####__######____##__
    >>> deserialize("outfiles/out4.txt", ["++", "--"],
    ... [2,5,1,3], [3,2,4,3], [1,3,4,2], [2,1,3,1])
    >>> with open("outfiles/out4.txt", "r") as outfile4:
    ...     print(outfile4.read().strip())
    ++++----------++------
    ++++++----++++++++------
    ++------++++++++----
    ++++--++++++--

    >>> deserialize("outfiles/out5.txt", ["zoinks", "scooby"],
    ... [1,1,1,1,2,1,3], [1,2,2,2,4], [2,1,4,1], [3,1,4,2,1])
    >>> with open("outfiles/out5.txt", "r") as outfile5:
    ...     print(outfile5.read().strip())
    zoinksscoobyzoinksscoobyzoinkszoinksscoobyzoinkszoinkszoinks
    zoinksscoobyscoobyzoinkszoinksscoobyscoobyzoinkszoinkszoinkszoinks
    zoinkszoinksscoobyzoinkszoinkszoinkszoinksscooby
    zoinkszoinkszoinksscoobyzoinkszoinkszoinkszoinksscoobyscoobyzoinks


    >>> deserialize("outfiles/out6.txt", ["gg", "ez"],
    ... [2,3,4,1], [1,2,3,2,2], [1,2,4,2,3])
    >>> with open("outfiles/out6.txt", "r") as outfile6:
    ...     print(outfile6.read().strip())
    ggggezezezggggggggez
    ggezezggggggezezgggg
    ggezezggggggggezezgggggg
    """
    with open(outpath, 'w') as file:
        file.write(''.join([''.join(
            [patterns[i % len(patterns)] * v for i, v in enumerate(lines)] + [
                '\n']) for lines in serialized_lines]))


# Question 6
def sequential_apply(nums, *instructions):
    """
    Given a list of numbers and an arbitrary number of "instructions" which are
    in the form of a tuple, apply the instructions to the list of numbers in
    the order that they are passed in

    Params
    -----------------------------------
    nums : list
        list of numbers
    *instructions : tuple
        tuples containing a string specifying the instruction and the arguments
        needed to complete the instruction

    Returns
    -----------------------------------
    list
        returns a new list with the instructions applied

    Examples of all instructions:
    [1, 2, 3, 4], ('add', 1) -> [2, 3, 4, 5]
    [1, 2, 3, 4], ('multiply', 2) -> [2, 4, 6, 8]
    [1, 2, 3, 4], ('insert', 1, 100) -> [1, 100, 2, 3, 4]
    [1, 2, 3, 4], ('remove', 1) -> [1, 3, 4]
    [1, 2, 3, 4], ('mean',) -> [2.5, 2.5, 2.5, 2.5]
    [1, 2, 3, 4], ('range',) -> [3, 3, 3, 3]

    >>> sequential_apply([1, 2, 3, 4], ('add', 1))
    [2, 3, 4, 5]
    >>> sequential_apply([3.3, 6.6, 7.7],
    ... ('insert', 1, 5.5), ('insert', 1, 4.4))
    [3.3, 4.4, 5.5, 6.6, 7.7]
    >>> sequential_apply([9.9, 1.3, 8.2, 4, 10],
    ... ('remove', 0), ('mean',), ('range',), ('add', 10))
    [10.0, 10.0, 10.0, 10.0]
    >>> sequential_apply([4,2,5,7.8,3,4],
    ... ('remove', 3), ('mean',), ('range',), ('add', 6))
    [6.0, 6.0, 6.0, 6.0, 6.0]
    >>> sequential_apply([3.9, 4.3, 8.3, 4, 3],
    ... ('mean',), ('insert', 4, 5.6), ('add', 3))
    [7.7, 7.7, 7.7, 7.7, 8.6, 7.7]
    >>> sequential_apply([3,6,3,6,2,6,3],
    ... ('range',), ('add', 4), ('remove', 2), ('multiply', 3))
    [24, 24, 24, 24, 24, 24]
    """
    tuple_insert_element = 2
    final = nums
    for i in instructions:
        if i[0] == 'add':
            final = [j + i[1] for j in final]
        elif i[0] == 'multiply':
            final = [j * i[1] for j in final]
        elif i[0] == 'insert':
            final.insert(i[1], i[tuple_insert_element])
        elif i[0] == 'remove':
            del final[i[1]]
        elif i[0] == 'mean':
            final = list(map(lambda x: int(x) if x % 1 == 0 else x,
                             [sum(final) / len(final) for j in final]))
        elif i[0] == 'range':
            final = [max(final) - min(final) for j in final]
    return final
