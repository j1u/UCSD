# DSC 20 Homework 01
# Name: James Lu
# PID: A16687580


# Question 1
def older_tutor(first_name, first_age, second_name, second_age):
    """
    A function that returns the name of the older tutor given their names (as
    strings) and ages (as positive integers). If they happen to be of the same
    age, return a string: 'Same Age'.
    >>> older_tutor("Sean", 22, "James", 21)
    'Sean'
    >>> older_tutor("Michelle", 18, "Yuri", 20)
    'Yuri'
    >>> older_tutor("Colin", 999, "Darren", 999)
    'Same Age'
    >>> older_tutor("Donovan", 50, "Jamal", 79)
    'Jamal'
    >>> older_tutor("Trae", 5555, "Ja", 5555)
    'Same Age'
    >>> older_tutor("Nikola", 85, "Joel", 32)
    'Nikola'
    """
    if first_age == second_age:
        return 'Same Age'
    elif first_age > second_age:
        return first_name
    else:
        return second_name


# Question 2
def older_tutor_year_month(f_name, f_year, f_month, s_name, s_year, s_month):
    """
    A function that that returns the name of the older tutor, given their
    names (as strings), years (as positive integers), and months (0 to 11).
    If they happen to be the same age return a string: 'Same Age'.
    You can reuse the function from the first question when needed.
    >>> older_tutor_year_month("Sean", 22, 10, "James", 22, 5)
    'Sean'
    >>> older_tutor_year_month("Michelle", 18, 11, "Yuri", 18, 11)
    'Same Age'
    >>> older_tutor_year_month("Colin", 10, 11, "Darren", 30, 3)
    'Darren'
    >>> older_tutor_year_month("Mike", 40, 0, "Hakeem", 22, 5)
    'Mike'
    >>> older_tutor_year_month("Wilt", 34, 11, "Jerry", 18, 11)
    'Wilt'
    >>> older_tutor_year_month("Magic", 86, 7, "Larry", 86, 3)
    'Magic'
    """
    if f_year == s_year:
        if f_month == s_month:
            return 'Same Age'
        elif f_month > s_month:
            return f_name
        else:
            return s_name
    elif f_year > s_year:
        return f_name
    else:
        return s_name


# Question 3
def message(name, dow, time):
    """
    A function that takes in (name, day of the week, time) and returns
    Darren's invitation to his discussion session. Check the doctest
    for the output string format.
    Note:
        <BLANKLINE> denotes a blank line in doctest.
        DO NOT append this token to the returned string.
    >>> print(message("Marina", "Wednesday", "11:00 AM"))
    Dear Marina,
    Please join our discussion on Wednesday at 11:00 AM.
    <BLANKLINE>
    Darren
    >>> print(message("LeBron", "Monday", "7:00 PM"))
    Dear LeBron,
    Please join our discussion on Monday at 7:00 PM.
    <BLANKLINE>
    Darren
    >>> print(message("Luka", "Thursday", "8:00 AM"))
    Dear Luka,
    Please join our discussion on Thursday at 8:00 AM.
    <BLANKLINE>
    Darren
    >>> print(message("Anthony", "Saturday", "9:00 PM"))
    Dear Anthony,
    Please join our discussion on Saturday at 9:00 PM.
    <BLANKLINE>
    Darren
    """
    return 'Dear {0},\nPlease join our discussion on {1} at {2}.\n\nDarren'.format(name, dow, time)


# Question 4
def larger_room(
    first_name,
    first_room_length,
    first_room_width,
    second_name,
    second_room_length,
    second_room_width,
):
    """
    A function that returns the name of the tutor living in a larger room,
    given their names (as strings), and their room dimensions (height and
    width as positive integers). If they happen to have the same room area,
    return a string: "Same Area".
    You may assume that their rooms are rectangular.
    >>> larger_room("Sean", 22, 5, "James", 22, 10)
    'James'
    >>> larger_room("Michelle", 18, 11, "Yuri", 20, 3)
    'Michelle'
    >>> larger_room("Colin", 30, 3, "Darren", 2, 45)
    'Same Area'
    >>> larger_room("Luka", 43, 34, "James", 54, 15)
    'Luka'
    >>> larger_room("Ja", 12, 20, "Westbrook", 10, 40)
    'Westbrook'
    >>> larger_room("Porter", 40, 6, "Barton", 6, 45)
    'Barton'
    """
    f_area = first_room_length * first_room_width
    s_area = second_room_length * second_room_width

    if f_area == s_area:
        return 'Same Area'
    elif f_area > s_area:
        return first_name
    else:
        return second_name


# Question 5
def larger_multidim_room(
    first_name, first_room_dims, second_name, second_room_dims
):
    """
    A function that returns the name of the tutor living in a larger room,
    given their names (as strings), and their room dimensions (as a list of
    positive integers). If they happen to have the same room volume,
    return a string: "Same Volume".
    You may assume two rooms have the same number of dimensions.
    >>> larger_multidim_room("Sean", [22, 5, 7, 11], "James", [10, 11, 2, 9])
    'Sean'
    >>> larger_multidim_room("Michelle", [11, 8, 1], "Yuri", [3, 5, 20])
    'Yuri'
    >>> larger_multidim_room("Colin", [20, 9], "Darren", [18, 10])
    'Same Volume'
    >>> larger_multidim_room("Mike", [23, 8, 7, 15], "John", [26, 11, 8, 3])
    'Mike'
    >>> larger_multidim_room("Dave", [13, 5, 8], "Fred", [17, 5, 11])
    'Fred'
    >>> larger_multidim_room("Gerald", [52, 11], "Julie", [45, 16])
    'Julie'
    """
    f_vol = s_vol = 1
    for i in range(len(first_room_dims)): #can use len of first room dim as they are assumed to be equal
        f_vol *= first_room_dims[i]
        s_vol *= second_room_dims[i]

    if f_vol == s_vol:
        return 'Same Volume'
    elif f_vol > s_vol:
        return first_name
    else:
        return second_name


# Question 6
def larger_room_subspace(
    ndim, first_name, first_room_dims, second_name, second_room_dims
):
    """
    A function that returns the name of the tutor living in a larger room with
    larger subspace (calculated using only the first `ndim` dimensions), given
    their names (as strings), and their room dimensions (as a list of positive
    integers). If they happen to have the same room volume, return a string:
    "Same Volume".
    You may assume two rooms have the same number of dimensions, and `ndim`
    won't exceed the number of dimensions of both rooms.
    >>> larger_room_subspace(2, "Sean", [2, 4, 6, 7, 8],
    ...                         "James", [4, 2, 8, 10, 3])
    'Same Volume'
    >>> larger_room_subspace(3, "Sean", [2, 4, 6, 7, 8],
    ...                         "James", [4, 2, 8, 10, 3])
    'James'
    >>> larger_room_subspace(5, "Sean", [2, 4, 6, 7, 8],
    ...                         "James", [4, 2, 8, 10, 3])
    'Sean'
    >>> larger_room_subspace(4, "Luka", [2, 6, 7, 9],
    ...                         "Ja", [4, 2, 10, 3])
    'Luka'
    >>> larger_room_subspace(3, "Trae", [2, 7, 8],
    ...                         "Fred", [4, 2, 3])
    'Trae'
    >>> larger_room_subspace(7, "Jokic", [2, 5, 2, 7, 8, 7, 4],
    ...                         "Jamal", [4, 7, 3, 10, 3, 3, 6])
    'Jamal'
    """
    f_vol = s_vol = 1
    for i in range(ndim): #can use len of first room dim as they are assumed to be equal
        f_vol *= first_room_dims[i]
        s_vol *= second_room_dims[i]

    if f_vol == s_vol:
        return 'Same Volume'
    elif f_vol > s_vol:
        return first_name
    else:
        return second_name


# Question 7
def larger_room_subspace_unbounded(
    ndim, first_name, first_room_dims, second_name, second_room_dims
):
    """
    A function that returns the name of the tutor living in a larger room with
    larger subspace (calculated using only the first `ndim` dimensions), given
    their names (as strings), and their room dimensions (as a list of positive
    integers). If they happen to have the same room volume, return a string:
    "Same Volume".
    If the given dimensions `ndim` exceeds the number of dimensions of both
    rooms, you should apply the following procedure to each room:
        (1) Find the dimensions with maximum and minimum length
        (2) Take the square root of the product of the max and min found
        (3) Truncate this number to only keep the 3 digits after the decimal
        point
    Then, compare the truncated numbers of two rooms, and return the name of
    the tutor associated with a larger truncated number, or "Same Volume"
    if two numbers are equal.
    You may assume two rooms have the same number of dimensions.
    >>> larger_room_subspace_unbounded(10, "Yuri", [2, 8, 6, 8, 9],
    ...                                    "James", [4, 1, 18, 15, 6])
    'Same Volume'
    >>> larger_room_subspace_unbounded(10, "Sean", [2, 4, 6, 7, 8],
    ...                                    "James", [4, 2, 8, 10, 3])
    'James'
    >>> larger_room_subspace_unbounded(10, "Jerry", [9, 7, 1, 2, 11],"Colin", [8, 5, 8, 3, 12])
    'Jerry'
    >>> larger_room_subspace_unbounded(5, "Luka", [2, 8, 6, 8, 9],
    ...                                    "Trae", [4, 1, 18, 15, 6])
    'Luka'
    >>> larger_room_subspace_unbounded(8, "Ja", [2, 4, 6, 7, 8],
    ...                                    "Jamal", [4, 2, 8, 10, 3])
    'Jamal'
    >>> larger_room_subspace_unbounded(6, "Jokic", [9, 7, 1, 2, 11, 4],"Joel", [8, 5, 8, 3, 12, 2])
    'Joel'
    """
    if ndim > len(first_room_dims):
        f_vol = int(str(round(((max(first_room_dims) * min(first_room_dims)) ** (1/2)), 3)).split('.')[1])
        s_vol = int(str(round(((max(second_room_dims) * min(second_room_dims)) ** (1/2)), 3)).split('.')[1])

        if f_vol == s_vol:
            return 'Same Volume'
        elif f_vol > s_vol:
            return first_name
        else:
            return second_name
    else:
        return larger_room_subspace(ndim, first_name, first_room_dims, second_name, second_room_dims)


# Question 8
def odd_even_list(names):
    """
    A function that returns a list, where each name in the names list is
    replaced with the string "Even" if the name has even length, or "Odd"
    otherwise. If the names list is empty, return a list with the string
    "Empty list was given" in it.
    >>> odd_even_list(["Marina", "Michelle", "James", "Darren"])
    ['Even', 'Even', 'Odd', 'Even']
    >>> odd_even_list(["Yuri", "Colin", "Sean"])
    ['Even', 'Odd', 'Even']
    >>> odd_even_list([])
    ['Empty list was given']
    >>> odd_even_list(["Joel", "Nurkic", "Gobert", "Jokic"])
    ['Even', 'Even', 'Even', 'Odd']
    >>> odd_even_list(["Jamal", "Ja", "Chris"])
    ['Odd', 'Even', 'Odd']
    >>> odd_even_list(["Barton"])
    ['Even']
    """
    if not names: #an empty list will return False
        return ['Empty list was given']
    else:
        odd_even = []
        for name in names:
            if not len(name) % 2: #an even number % 2 will return 0 so for this boolean to be True we need the negation
                odd_even.append('Even')
            else:
                odd_even.append('Odd')
        return odd_even


# Question 9
def is_james_more_than_sean(names):
    """
    A function that returns whether the name 'James' occurs more often than
    the name 'Sean' in the input list of names. Return True if the above
    statement is true.
    >>> is_james_more_than_sean(["James", "Sean", "James"])
    True
    >>> is_james_more_than_sean(["Sean", "Sean"])
    False
    >>> is_james_more_than_sean(["Sean", "Marina", "Yuri", "James"])
    False
    >>> is_james_more_than_sean(["James", "Sean", "James", "James", "Sean"])
    True
    >>> is_james_more_than_sean(["Sean", "Sean", "Sean", "James"])
    False
    >>> is_james_more_than_sean(["Sean", "Marina", "Yuri", "James", "James", "Sean"])
    False
    """
    return names.count('James') > names.count('Sean')


# Question 10
def string_sum(lst):
    """
    A function that calculates the sum of all integers in the input list. All
    integers in the input list are given in string format, and the returned
    sum should also be a string.
    >>> string_sum(["1", "2", "3"])
    '6'
    >>> string_sum(["111", "205", "377"])
    '693'
    >>> string_sum(["777", "-999"])
    '-222'
    >>> string_sum(["55", "-7", "15"])
    '63'
    >>> string_sum(["353", "-274", "456"])
    '535'
    >>> string_sum(["993", "-1774"])
    '-781'
    """
    num = 0
    for i in lst:
        num += int(i)
    return str(num)
    