"""
DSC 20 Homework 04
"""


# Utility Function
def is_iterable(obj):
    """
    A function that checks if `obj` is a iterable (can be iterated over
    in a for-loop).
    DO NOT MODIFY THIS FUNCTION. You don't need to add new doctests
    for this function.
    >>> is_iterable(1)
    False
    >>> is_iterable("DSC 20")
    True
    >>> is_iterable(["Fall", 2020])
    True
    """
    try:
        iter(obj)
        return True
    except TypeError:
        return False


# Question 1
def yelp_filter(restaurants, x_coord, y_coord):
    """
    Given a list of Yelp restaurants information and current xy-coordinates
    as two positive integers return a list of the names of restaurants that are
    <= 2 miles of the x, y coords, have a 4+ star rating, is open, and is
    Mexican, Hawaiian, or Asian Fusion

    Params
    -----------------------------------
    restaurants : tuple
        information of a restaurant in the form:
        (name, stars, restaurant_coords, is_open, category)
    x_coord : int
        positive int representing the persons x coordinate
    y_coord : int
        positive int representing the persons y coordinate

    Returns
    -----------------------------------
    list
        returns a list containing the names of all the restaurants that meet
        the criteria described above

    >>> restaurants = [("The Taco Stand", 4.5, (3, 4), True, "Mexican"),
    ... ("Pho La Jolla", 3.9, (5, 3), True, "Asian Fusion"),
    ... ("Philz Coffee", 4.5, (5, 3), True, "Cafes"),
    ... ("Chipotle", 4.1, (4, 3), False, "Mexican"),
    ... ("Poki One N Half", 4.6, (5, 3), True, "Hawaiian")]
    >>> yelp_filter(restaurants, 4, 4)
    ['The Taco Stand', 'Poki One N Half']
    >>> yelp_filter(restaurants, 6, 3)
    ['Poki One N Half']
    >>> yelp_filter(restaurants, 3, 6)
    ['The Taco Stand']
    >>> yelp_filter(restaurants, 1, 7)
    []
    >>> yelp_filter(restaurants, -1, 3)
    Traceback (most recent call last):
    AssertionError
    """
    assert ((isinstance(restaurants, list)) & (
        all(map(lambda x: isinstance(x, tuple), restaurants))) & (
                    x_coord > 0) & (y_coord > 0))
    restaurant_coords, is_open, category = 2, 3, 4
    dist = lambda tup, x, y: (((tup[0] - x) ** 2) + (
            (tup[1] - y) ** 2)) ** 1 / 2
    is_category = lambda \
            categ: categ == 'Mexican' or categ == 'Hawaiian' or \
                   categ == 'Asian Fusion'
    return list(map(lambda y: y[0], filter(
        lambda x: (dist(x[restaurant_coords], x_coord, y_coord) <= 2) & (
                x[1] >= 4) & x[is_open] & is_category(x[category]),
        restaurants)))


# Question 2
def create_dsc_email(students, years):
    """
    Given a tuple in the form (name, class_year, college, major_code)
    this function extracts the first 3 letters of the first name
    the first 6 letters of the last name, the last 2 digits of the class year,
    the 2-character abbreviation of the college and concatenates them together
    and adds “@dsc.ucsd.edu' at the end, returns a dictionary with the students
    full name and newly created email address

    Params
    -----------------------------------
    students : tuple
        information of a student in the form:
        (name, class_year, college, major_code)
    years : list
        list of class years that students must be in to receive a new email
        address

    Returns
    -----------------------------------
    dict
        returns a dictionary with the full student name as the key and the
        newly generated email address as the value

    >>> students = [ \
        ("First Middle Last", 2022, "revelle", "DS25"), \
        ("hi HELLO", 2022, "seventh", "DS25"), \
        ("Computer Science", 2021, "Warren", "CS25"), \
        ("longfirstname longlastname", 1990, "Marshall", "DS25") \
    ]
    >>> students2 = [ \
        ("Bong bong vbsd", 2022, "roosevelt", "DS25"), \
        ("hola fosefs", 2022, "warren", "cS25"), \
        ("Computer hecka Science", 2020, "Warren", "DS25"), \
        ("fsdfsdf asdfasdf", 2021, "Marshall", "DS25") \
    ]
    >>> create_dsc_email(students, [2022])
    {'First Middle Last': 'firlast22rc@dsc.ucsd.edu', \
'hi HELLO': 'hihello22sv@dsc.ucsd.edu'}
    >>> create_dsc_email(students, [1990, 2021])
    {'longfirstname longlastname': 'lonlongla90tm@dsc.ucsd.edu'}
    >>> create_dsc_email(students, [])
    {}
    >>> create_dsc_email(students2, [2022, 2021])
    {'Bong bong vbsd': 'bonvbsd22er@dsc.ucsd.edu', \
'fsdfsdf asdfasdf': 'fsdasdfas21tm@dsc.ucsd.edu'}
    >>> create_dsc_email(students2, [1990])
    {}
    >>> create_dsc_email(students2, [2020, 1990])
    {'Computer hecka Science': 'comscienc20wc@dsc.ucsd.edu'}
    """
    college_i, major_i, yr_i = 2, 3, 2
    first_name_chars, last_name_chars = 3, 6
    abbrev = {'revelle': 'rc', 'muir': 'mc', 'marshall': 'tm', 'warren': 'wc',
              'roosevelt': 'er', 'sixth': 'sx', 'seventh': 'sv'}
    new_dict = {}

    filtered = list(
        filter(lambda x: (x[1] in years) & (x[major_i] == 'DS25'), students))

    names = list(map(lambda x: x[0].split(' '), filtered))
    first_name = list(map(
        lambda x: x[0] if len(x[0]) <= first_name_chars else x[0][
                                                        0:first_name_chars],
        names))
    last_name = list(map(
        lambda x: x[-1] if len(x[-1]) <= last_name_chars else x[-1][
                                                         0:last_name_chars],
        names))
    yr = list(map(lambda x: str(x[1])[yr_i:], filtered))
    college = list(map(lambda x: abbrev.get(str.lower(x[college_i])), filtered))
    emails = list(map(lambda first, last, year, col: str.lower(
        first + last + year + col) + '@dsc.ucsd.edu', first_name, last_name, yr,
                      college))
    list(map(lambda x, y: new_dict.update({x: y}),
             list(map(lambda x: ' '.join(x), names)), emails))
    return new_dict


# Question 3
def base_converter(target_base):
    """
    Base converter function that takes a target base (integer between 2 and 36,
    both inclusive), and returns a function that takes a non-negative integer in
    decimal (base-10), and converts it to the target base as a string.

    Params
    -----------------------------------
    target base : integer
        integer in the interval [2, 36]

    Returns
    -----------------------------------
    function
        returns a function that takes a non-negative integer in decimal
        (base-10), and converts it to the target base as a string.

    >>> binary_converter = base_converter(2)
    >>> [binary_converter(i) for i in range(10)]
    ['0', '1', '10', '11', '100', '101', '110', '111', '1000', '1001']
    >>> base_converter(8)(227)
    '343'
    >>> base_converter(36)(227)
    '6B'
    >>> base_converter(6)(345)
    '1333'
    >>> base_converter(5)(742)
    '10432'
    >>> base_converter(16)(4351)
    '10FF'
    """
    base_min, base_max = 2, 36
    assert ((target_base >= base_min) & (target_base <= base_max))
    base_limit = 10
    letter_conversion = {k + base_limit: v for k, v in enumerate(
        [chr(i) for i in range(ord('A'), ord('Z') + 1)])}

    """
    Function that takes a non-negative integer in
    decimal (base-10), and converts it to the target base as a string.

    Params
    -----------------------------------
    integer : integer
        non negative integer

    Returns
    -----------------------------------
    string
        returns the integer converted to the target base in
        the form of a string.

    """
    def base_conversion(integer):
        assert (isinstance(integer, int))
        temp = integer
        converted = ''

        while temp // target_base > 0:
            rem = temp % target_base
            if target_base > base_limit:
                if rem < base_limit:
                    converted += str(rem)
                else:
                    converted += str(letter_conversion.get(rem))
            else:
                converted += str(rem)
            temp = temp // target_base
        if temp // target_base == 0:
            converted += str(temp % target_base)
        return converted[::-1]

    return base_conversion


# Question 4
def magic_sequence_generator(start0, start1, start2):
    """
    A generator that outputs an infinite magic sequence S(i) of non-negative
    integers, where the first three integers are the arguments (`s0` for the
    first integer S(0), `s1` for the second integer S(1), `s2` for third
    integer S(2)), and other integers at index i (S(i)) is the maximum value
    between (S(i-3) - S(i-2)) and (S(i-3) - S(i-1)).

    Params
    -----------------------------------
    start0 : int
        first integer in sequence
    start1 : int
        second integer in sequence
    start2 : int
        third integer in sequence

    Returns
    -----------------------------------
    generator
        returns a generator that yields the next magic number in the sequence

    >>> gen = magic_sequence_generator(30, 20, 10)
    >>> [next(gen) for _ in range(3)]
    [30, 20, 10]
    >>> next(gen)
    20
    >>> [next(gen) for _ in range(10)]
    [10, 0, 20, 10, -10, 30, 20, -30, 60, 50]
    >>> [next(gen) for _ in range(10)]
    [-80, 140, 130, -210, 350, 340, -550, 900, 890, -1440]
    >>> [next(gen) for _ in range(10)]
    [2340, 2330, -3770, 6110, 6100, -9870, 15980, 15970, -25840, 41820]
    >>> [next(gen) for _ in range(10)]
    [41810, -67650, 109470, 109460, -177110, 286580, 286570, -463680, 750260, \
750250]
    """
    assert (isinstance(start0, int) & isinstance(start1, int) &
            isinstance(start2, int))
    seq = [start0, start1, start2]
    for i in seq:
        yield i
    while True:
        seq_idx_3 = 2
        new = max(seq[0] - seq[1], seq[0] - seq[seq_idx_3])
        seq.pop(0)
        seq.append(new)
        yield new


# Question 5
def round_robin_generator(k, arg1, arg2, arg3):
    """
    Given a positive integer k and three iterable objects, write a generator
    that, in each round, yields the next k elements from each iterable object
    in order, and repeats such rounds.

    Params
    -----------------------------------
    k : integer
        number of items to yield from each iterable arg
    arg1 : iterable
        an iterable object
    arg2 : iterable
        an iterable object
    arg3 : iterable
        an iterable object

    Returns
    -----------------------------------
    generator
        returns a generator that yields k number of times from each iterable
        passed

    >>> arg1 = "abcdefgh"
    >>> arg2 = [1, 2, 3, 4, 5, 6, 7, 8, 9]
    >>> arg3 = (True, False, True, False, True, False)
    >>> gen = round_robin_generator(2, arg1, arg2, arg3)
    >>> [next(gen, None) for _ in range(14)]
    ['a', 'b', 1, 2, True, False, 'c', 'd', 3, 4, True, False, 'e', 'f']
    >>> gen = round_robin_generator(3, arg1, arg2, arg3)
    >>> [next(gen, None) for _ in range(14)]
    ['a', 'b', 'c', 1, 2, 3, True, False, True, 'd', 'e', 'f', 4, 5]
    >>> arg4 = "dsc"
    >>> arg5 = [2, 0]
    >>> arg6 = "fall"
    >>> gen = round_robin_generator(4, arg4, arg5, arg6)
    >>> [next(gen, None) for _ in range(10)]
    ['d', 's', 'c', None, 2, 0, None, None, 'f', 'a']
    >>> arg7 = ["dsc", 'sdfsadf', 'asdfasdf']
    >>> arg8 = [2, 0, 5, 4, 2]
    >>> arg9 = ["fall", 'asdfsdf']
    >>> gen = round_robin_generator(2, arg7, arg8, arg9)
    >>> [next(gen, None) for _ in range(10)]
    ['dsc', 'sdfsadf', 2, 0, 'fall', 'asdfsdf', 'asdfasdf', None, 5, 4]
    >>> arg10 = ["dsc", 'asdf', 'sadfffss']
    >>> arg11 = [2, 0, 45, 34, 12]
    >>> arg12 = ["fall", 'werasd', 'asdf']
    >>> gen = round_robin_generator(1, arg10, arg11, arg12)
    >>> [next(gen, None) for _ in range(10)]
    ['dsc', 2, 'fall', 'asdf', 0, 'werasd', 'sadfffss', 45, 'asdf', None]
    >>> arg13 = ["dsc", 23, 5, 1 ,252]
    >>> arg14 = [2, 0, 'asdf', 'asdfs']
    >>> arg15 = "fallsdfsder"
    >>> gen = round_robin_generator(3, arg13, arg14, arg15)
    >>> [next(gen, None) for _ in range(10)]
    ['dsc', 23, 5, 2, 0, 'asdf', 'f', 'a', 'l', 1]
    """
    a = (i for i in arg1)
    b = (i for i in arg2)
    c = (i for i in arg3)

    while True:
        for i in range(k):
            yield next(a, None)
        for i in range(k):
            yield next(b, None)
        for i in range(k):
            yield next(c, None)


# Question 6
def make_generator(*args):
    """
    If three non-negative integers are passed as arguments, then they are
    passed to the magic_sequence_generator function and the generator is
    returned, if three iterables are passed as arguments (the type of each
    argument can be different from others), they are passed to the
    round_robin_generator function with k = 2 and the generator is returned,
    else return a generator that yields all arguments at odd indices first,
    then all arguments at even indices.

    Params
    -----------------------------------
    *args : integers, iterables
        args from which to create a generator

    Returns
    -----------------------------------
    generator
        returns a generator following the outlined principles above

    >>> gen1 = make_generator(30, 20, 10)
    >>> [next(gen1, None) for _ in range(10)]
    [30, 20, 10, 20, 10, 0, 20, 10, -10, 30]
    >>> gen2 = make_generator([10, 20], "Sean", [True, False])
    >>> [next(gen2, None) for _ in range(10)]
    [10, 20, 'S', 'e', True, False, None, None, 'a', 'n']
    >>> gen3 = make_generator("Ev", 0, ["en"], ("DD",))
    >>> [next(gen3, None) for _ in range(10)]
    [0, ('DD',), 'Ev', ['en'], None, None, None, None, None, None]
    >>> gen4 = make_generator("fds", 6, ["sdf", 'asdf'], 32)
    >>> [next(gen4, None) for _ in range(6)]
    [6, 32, 'fds', ['sdf', 'asdf'], None, None]
    >>> gen5 = make_generator(42, 'sdfss', 3, ["en"], ("bongk",))
    >>> [next(gen5, None) for _ in range(7)]
    ['sdfss', ['en'], 42, 3, ('bongk',), None, None]
    >>> gen6 = make_generator(3, 6, 'dfs', 3, (23,), 0, ["en"], ("DD",))
    >>> [next(gen6, None) for _ in range(5)]
    [6, 3, 0, ('DD',), 3]
    """
    len_of_arg = 3
    arg3 = 2
    if (len(args) == len_of_arg) & all([(isinstance(x, int)) for x in args]):
        yield from magic_sequence_generator(args[0], args[1], args[arg3])
    elif (len(args) == len_of_arg) & all([is_iterable(x) for x in args]):
        specified_k = 2
        yield from round_robin_generator(specified_k, args[0], args[1],
                                         args[arg3])
    else:
        skip_by = 2
        odd = (i for i in args[1::skip_by])
        for i in odd:
            yield i
        even = (i for i in args[0::skip_by])
        for i in even:
            yield i


# Question 7
def skip_increasing(iterable, k):
    """
    Takes an iterable object and returns a list of its values, skipping values
    in between by an increasing amount (starting with 0, increment by 1 once
    collected a value), returns the output values as a list once the input
    has no more items, or k elements is reached in the output list.

    Params
    -----------------------------------
    iterable : iterable
        an iterable object to be converted into a list
    k : integer
        number of elements to be in the output list

    Returns
    -----------------------------------
    list
        returns a list of the elements in the iterable

    >>> skip_increasing(iter([1,2,3,4,5,6,7,8,9,10,11]), 5)
    [1, 2, 4, 7, 11]
    >>> skip_increasing(iter('ABcDefGhijKlmnoPqrs'), 10)
    ['A', 'B', 'D', 'G', 'K', 'P']
    >>> skip_increasing(iter((1, None, 3, 4, 5, 6, 7, 8)), 3)
    [1, None, 4]
    >>> skip_increasing(iter((1, '43', 3, 6, 4, 6, 6, 3, 5, 9)), 7)
    [1, '43', 6, 6]
    >>> skip_increasing(iter('lotsofwordsandthingsThatasusa'), 10)
    ['l', 'o', 's', 'w', 's', 'h', 'h', 'a']
    >>> skip_increasing(iter(['Hello', 'My', 'name', 'is', 'is']), 5)
    ['Hello', 'My', 'is']
    """
    hop_length = 0
    ls = []
    it = (i for i in iterable)
    num_calls = 0
    while True:
        if len(ls) >= k:
            break
        nxt = next(it, '****break****')
        if nxt == '****break****':
            break
        ls.append(nxt)
        num_calls += 1
        for j in range(hop_length):
            next(it, None)
            num_calls += 1
        hop_length += 1
    return ls
