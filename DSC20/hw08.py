"""
DSC 20 Homework 08
"""


# Question 1
def course_doctests():
    """
    Doctests for Course.
    >>> dsc20 = Course("DSC20", "Intro to Programming", "Marina Langlois",
    ... 150, False)
    >>> print(dsc20)
    DSC20: Intro to Programming, a lower-division course instructed by Marina Langlois, allows 150 students to enroll.
    >>> dsc20
    Course('DSC20', 'Intro to Programming', 'Marina Langlois', 150, False)

    >>> dsc106 = Course("DSC106", "Intro to Data Visualization",
    ... "Thomas Powell", 100, True)
    >>> print(dsc106)
    DSC106: Intro to Data Visualization, an upper-division course instructed by Thomas Powell, allows 100 students to enroll.
    >>> dsc106
    Course('DSC106', 'Intro to Data Visualization', 'Thomas Powell', 100, True)

    >>> dsc80 = Course("DSC80", "Some Course",
    ... "Some Prof", 150, True)
    >>> print(dsc80)
    DSC80: Some Course, an upper-division course instructed by Some Prof, allows 150 students to enroll.
    >>> dsc80
    Course('DSC80', 'Some Course', 'Some Prof', 150, True)
    >>> dsc50 = Course("DSC50", "Other Course",
    ... "A Prof", 150, False)
    >>> print(dsc50)
    DSC50: Other Course, a lower-division course instructed by A Prof, allows 150 students to enroll.
    >>> dsc50
    Course('DSC50', 'Other Course', 'A Prof', 150, False)
    >>> dsc10 = Course("DSC10", "Intro Course",
    ... "Another Prof", 150, False)
    >>> print(dsc10)
    DSC10: Intro Course, a lower-division course instructed by Another Prof, allows 150 students to enroll.
    >>> dsc10
    Course('DSC10', 'Intro Course', 'Another Prof', 150, False)
    """
    return


class Course:
    """
    Class used to represent a course at UCSD.

    Attributes
    ---------------------------------------
    course_code : str
        The code associated with the course
    name : str
        The name of the course
    instructor : str
        The name of the instructor
    enrollment_limit : int
        The maximum number of students allowed to enroll in the course
    is_upperdiv : boolean
        Indicates whether the course is an upper division course
    """

    def __init__(self, course_code, name, instructor, \
                 enrollment_limit, is_upperdiv):
        """
        Constructor of Course class. DO NOT CHANGE THIS.
        """
        self.course_code = course_code
        self.name = name
        self.instructor = instructor
        self.enrollment_limit = enrollment_limit
        self.is_upperdiv = is_upperdiv

    def __str__(self):
        """
        String attribute that returns a string representation of a Course object
        """
        string = '{}: {}, {} course instructed by {}, allows {} students ' \
                 'to enroll.'

        if self.is_upperdiv:
            return string.format(self.course_code, self.name,
                                 'an upper-division', self.instructor,
                                 self.enrollment_limit)
        else:
            return string.format(self.course_code, self.name,
                                 'a lower-division', self.instructor,
                                 self.enrollment_limit)

    def __repr__(self):
        """
        Repr attribute that returns a representation of a Course object
        """
        rep = 'Course({}, {}, {}, {}, {})'
        return rep.format(repr(self.course_code), repr(self.name),
                          repr(self.instructor), self.enrollment_limit,
                          self.is_upperdiv)


# Question 2
def find_two_sums_rec(main, sub):
    """
    Recursive function that takes two sequences of numeric values
    (main and sub), and calculates two kinds of sum of the main sequence:
        Sum of intersection: the sum of all numbers in the main sequence that
        also appear in the sub sequence.
        Sum of differences: the sum of all numbers in the main sequence that do
        not appear in the sub sequence.
    Returns a tuple of (sum_of_intersection, sum_of_difference), but if the
    main sequence is empty, return (0, 0).

    Params
    ---------------------------------------
    main : list
        list of main values to be compared
    sub : list
        list of sub values to be compared to

    Return
    ---------------------------------------
    sum : tuple
        2 element tuple containing the sums of intersection and the sums of
        difference between the two lists

    >>> main_seq = [0, 1, 1, 2, 3, 3, 4, 5, 5]
    >>> find_two_sums_rec(main_seq, [])
    (0, 24)
    >>> find_two_sums_rec(main_seq, [1, 2])
    (4, 20)
    >>> find_two_sums_rec(main_seq, [3, 4, 5])
    (20, 4)

    >>> find_two_sums_rec(main_seq, [4, 5, 7])
    (14, 10)
    >>> find_two_sums_rec(main_seq, [2, 0, 4])
    (6, 18)
    >>> find_two_sums_rec(main_seq, [0, 6, 2])
    (2, 22)
    """
    final = [0, 0]

    def inner(m, s, f):
        if len(m) < 1:
            return tuple(f)
        if m[0] in s:
            f[0] += m[0]
        else:
            f[1] += m[0]
        return inner(m[1:], s, f)

    return inner(main, sub, final)


# Question 3
def counter_doctests():
    """
    Doctests for Counter and AlphanumericCounter.
    >>> counter = Counter()
    >>> counter.size()
    0
    >>> counter.add_items([123, 123, "abc", (10, 10), (10, 20)])
    >>> counter.size()
    5
    >>> counter.get_count(123)
    2
    >>> counter.get_count("dsc20")
    0
    >>> counter.get_all_counts()
    {123: 2, 'abc': 1, (10, 10): 1, (10, 20): 1}
    >>> an_counter = AlphanumericCounter()
    >>> an_counter.size()
    0
    >>> len(an_counter.counts)
    62
    >>> an_counter.add_items("DSC 20 (Marina Langlois)")
    >>> an_counter.size()
    19
    >>> an_counter.get_count("a")
    3
    >>> an_counter.get_count("?")
    0
    >>> an_counter.get_all_counts()
    {'0': 1, '2': 1, 'a': 3, 'g': 1, 'i': 2, 'l': 1, 'n': 2, 'o': 1, \
'r': 1, 's': 1, 'C': 1, 'D': 1, 'L': 1, 'M': 1, 'S': 1}

    >>> counter2 = Counter()
    >>> counter2.size()
    0
    >>> counter2.add_items([123, 123, "abc", (10, 10), (10, 20)])
    >>> counter2.get_all_counts()
    {123: 2, 'abc': 1, (10, 10): 1, (10, 20): 1}
    >>> counter2.size()
    5
    >>> counter2.add_items([3, 4, "abc", (7, 2), (6, 2)])
    >>> counter2.get_all_counts()
    {123: 2, 'abc': 2, (10, 10): 1, (10, 20): 1, 3: 1, 4: 1, (7, 2): 1, (6, 2): 1}
    >>> counter2.add_items(['asd', 123, "cba", (2, 5), (4, 8)])
    >>> counter2.get_all_counts()
    {123: 3, 'abc': 2, (10, 10): 1, (10, 20): 1, 3: 1, 4: 1, (7, 2): 1, (6, 2): 1, 'asd': 1, 'cba': 1, (2, 5): 1, (4, 8): 1}
    >>> counter2.size()
    15
    >>> counter2.get_count(123)
    3
    >>> counter2.get_count("dsc20")
    0
    >>> counter2.get_count(3)
    1

    >>> an_counter2 = AlphanumericCounter()
    >>> an_counter2.size()
    0
    >>> len(an_counter2.counts)
    62
    >>> an_counter2.add_items("A bunch of words")
    >>> an_counter2.get_all_counts()
    {'b': 1, 'c': 1, 'd': 1, 'f': 1, 'h': 1, 'n': 1, 'o': 2, 'r': 1, 's': \
1, 'u': 1, 'w': 1, 'A': 1}

    >>> an_counter2.add_items("Another bunch of words")
    >>> an_counter2.get_all_counts()
    {'b': 2, 'c': 2, 'd': 2, 'e': 1, 'f': 2, 'h': 3, 'n': 3, 'o': 5, \
'r': 3, 's': 2, 't': 1, 'u': 2, 'w': 2, 'A': 2}

    >>> an_counter2.size()
    32
    >>> an_counter2.add_items("A whole lotta words")
    >>> an_counter2.get_all_counts()
    {'a': 1, 'b': 2, 'c': 2, 'd': 3, 'e': 2, 'f': 2, 'h': 4, 'l': 2, \
'n': 3, 'o': 8, 'r': 4, 's': 3, 't': 3, 'u': 2, 'w': 4, 'A': 3}

    >>> an_counter2.size()
    48
    >>> an_counter2.get_count("a")
    1
    >>> an_counter2.get_count("?")
    0
    >>> an_counter2.get_count("w")
    4
    >>> an_counter2.get_all_counts()
    {'a': 1, 'b': 2, 'c': 2, 'd': 3, 'e': 2, 'f': 2, 'h': 4, 'l': 2, \
'n': 3, 'o': 8, 'r': 4, 's': 3, 't': 3, 'u': 2, 'w': 4, 'A': 3}

    >>> an_counter2.get_char(0)
    '0'
    >>> an_counter2.get_char(25)
    'p'
    >>> an_counter2.get_char(56)
    'U'
    >>> an_counter2.get_index("1")
    1
    >>> an_counter2.get_index("f")
    15
    >>> an_counter2.get_index("F")
    41
    """
    return


class Counter:
    """
    Class that abstracts a generalized counter for all kinds of iterable
    objects.

    Attributes
    ---------------------------------------
    nelems : int
        integer representing the total number of elements in the object
    counts : dict
        dictionary containing items (keys) and their counts (values)

    Methods
    ---------------------------------------
    size()
        Returns the total number of items stored in the counter.
    get_count(item)
        Returns the count of an object item. If the item does not exist in
        the counter, return 0.
    get_all_counts()
        Returns a dictionary of all item to count pairs.
    add_items(items)
        Takes an iterable object (like list) of objects (items) and adds them
        to the counter. Updates both count and nelems attributes.

    """

    def __init__(self):
        """
        Constructor of Counter class
        """
        self.nelems = 0
        self.counts = {}

    def size(self):
        """
        Method that returns the total number of items stored in the counter

        Return
        ---------------------------------------
        size : int
            integer representing the total number of items in the counter
        """
        return self.nelems

    def get_count(self, item):
        """
        Method that returns the count of an item stored in the counter

        Params
        ---------------------------------------
        item : obj
            item to be found in counter

        Return
        ---------------------------------------
        count : int
            integer representing the count of the item
        """
        if item in self.counts.keys():
            return self.counts.get(item)
        else:
            return 0

    def get_all_counts(self):
        """
        Method that returns a dictionary containing the counts of all objects
        in the counter

        Return
        ---------------------------------------
        counts : dict
            dictionary containing the counts of all objects
        """
        return self.counts.copy()

    def add_items(self, items):
        """
        Method that adds items into the counter

        Params
        ---------------------------------------
        items : iterable
            iterable object containing the items to be added into the counter
        """
        for itm in items:
            if itm in self.counts.keys():
                self.counts[itm] += 1
                self.nelems += 1
            else:
                self.counts[itm] = 1
                self.nelems += 1


class AlphanumericCounter(Counter):
    """
    Class that acts as a counter for alphanumeric iterable objects.

    Attributes
    ---------------------------------------
    nelems : int
        integer representing the total number of elements in the object
    counts : list
        list where indices 0 - 9 represent integers 0 - 9, 10 - 35 represent
        lowercase letters, and 36 - 61 represent uppercase letters, with the
        value at each index representing the count

    Methods
    ---------------------------------------
    size()
        Returns the total number of items stored in the counter.
    get_index(self, item)
        Given an item, return its corresponding index (0-9 for digits, 10-35
        for lowercase letters, 36-61 for uppercase letters). If the item is
        not an alphanumeric character, return -1.
    get_char(self, index)
        Given an index (0-61), return the corresponding character.
    get_count(item)
        Returns the count of an object item. If the item does not exist in
        the counter, return 0.
    get_all_counts()
        Returns a dictionary of all item to count pairs.
    add_items(items)
        Takes an iterable object (like list) of objects (items) and adds them
        to the counter. Updates both count and nelems attributes.
    """

    def __init__(self):
        """
        Constructor of AlphaNumericCounter class
        """
        super().__init__()
        self.counts = [0] * 62

    def get_index(self, item):
        """
        Given an item this method returns its corresponding index
        (0-9 for digits, 10-35 for lowercase letters, 36-61 for uppercase
        letters). If the item is not an alphanumeric character, return -1.

        Params
        ---------------------------------------
        item : chr
            item to be converted to index

        Return
        ---------------------------------------
        idx : int
            integer representing the index of the item
        """
        integer_mapping = 48
        lower_mapping = 87
        upper_mapping = 29
        if item.isnumeric():
            return ord(item) - integer_mapping
        elif item.islower():
            return ord(item) - lower_mapping
        elif item.isupper():
            return ord(item) - upper_mapping
        else:
            return -1

    def get_char(self, idx):
        """
        Given an index (0-61) this method returns the corresponding character.

        Params
        ---------------------------------------
        idx : int
            item to be converted to index

        Return
        ---------------------------------------
        item : chr
            item representing a converted index
        """
        integer_mapping = 48
        lower_mapping = 87
        upper_mapping = 29
        if 0 <= idx < 10:
            return chr(idx + integer_mapping)
        elif 10 <= idx < 36:
            return chr(idx + lower_mapping)
        elif 36 <= idx < 62:
            return chr(idx + upper_mapping)

    def get_count(self, item):
        """
        Returns the count of a character item. If the item does not exist
        in the counter method returns 0.

        Params
        ---------------------------------------
        item : chr
            item to be counted

        Return
        ---------------------------------------
        count : int
            integer representing the count of the item
        """
        if 0 <= self.get_index(item) < 62:
            return self.counts[self.get_index(item)]
        else:
            return 0

    def get_all_counts(self):
        """
        Method that returns a dictionary containing the counts of all objects
        in the counter

        Return
        ---------------------------------------
        counts : dict
            dictionary containing the counts of all objects
        """
        return {self.get_char(k): v for k, v in enumerate(self.counts) if v > 0}

    def add_items(self, items):
        """
        Method that adds items into the counter

        Params
        ---------------------------------------
        items : iterable
            iterable object containing the items to be added into the counter
        """
        for itm in items:
            if 0 <= self.get_index(itm) < 62:
                self.counts[self.get_index(itm)] += 1
                self.nelems += 1


# Question 4 (Optional Practice Question)
def compute_max_string(base, pattern):
    """
    # TODO: Add method description and at least 3 new doctests #
    >>> compute_max_string("jumpsjump", "jump")
    9
    >>> compute_max_string("hwhwhw", "hwh")
    5
    >>> compute_max_string("frontsdakonsakdna", "front")
    5
    >>> compute_max_string("life", "life")
    4
    """


# Question 5 (Extra Credit)
def group_summation(nums, target):
    """
    Recursive function that takes a list of positive integers
    (nums, not empty) and a positive integer target, and determines whether it’s
    possible to pick a combination of integers from nums, such that their
    sum equals the target. Return True if we can pick such a combination of
    numbers from nums; otherwise, return False.

    Params
    ---------------------------------------
    nums : list
        list of numbers to be checked
    target : int
        target sum

    Return
    ---------------------------------------
    decision : boolean
        Return True if a sum that equals the target is possible, else
        return False.

    >>> group_summation([3, 34, 4, 12, 5, 2], 9)
    True
    >>> group_summation([1, 1, 1], 9)
    False
    >>> group_summation([1, 10, 9, 8], 17)
    True

    >>> group_summation([1,5,7,3,8], 17)
    False
    >>> group_summation([4,2,5,8,3], 9)
    True
    >>> group_summation([5,3,1,54,8], 53)
    False
    """
    nums = sorted(nums, reverse=True)
    if nums[0] == target:
        return True
    if len(nums) < 2:
        return False
    if nums[0] > target:
        return group_summation(nums[1:], target)
    if nums[0] < target:
        s = 0
        for i in range(len(nums)):
            s += nums[i]
            if s == target:
                return True
            if s > target:
                return group_summation(nums[1:], target)
            if sum(nums) < target:
                return False
