"""
DSC 20 Homework 05
"""


# Question 1
def complexity_tf():
    """
    Write your answers to time complexity True/False questions in this
    function.
    You don't need to add new doctests for this function.
    >>> answers = complexity_tf()
    >>> isinstance(answers, list)
    True
    >>> len(answers)
    10
    >>> all([isinstance(ans, bool) for ans in answers])
    True
    """
    return [False, True, True, True, False, True, False, True, True, False]


# Question 2
def complexity_mc():
    """
    Write your answers to time complexity multiple-choice questions in this
    function.
    You don't need to add new doctests for this function.
    1: O(1) - constant time
    2: O(log n) - logarithmic time
    3: O(n) - linear time
    4: O(n log n) - linearithmic time
    5: O(n^2) - quadratic time
    6: O(n^3), O(n^4), etc. - polynomial time
    7: O(2^n), O(3^n), etc. - exponential time

    >>> answers = complexity_mc()
    >>> isinstance(answers, list)
    True
    >>> len(answers)
    10
    >>> all([isinstance(ans, int) and 1 <= ans <= 7 for ans in answers])
    True
    """
    return [1, 6, 4, 3, 5, 2, 5, 3, 5, 7]


# Question 3
def advanced_search(data, max_avg, min_range):
    """
    Function that takes a data dictionary (with string as key,
    and non-empty list of numeric values as value) and three requirements
    for the search, return a list of all keys that satisfy all requirements.

    Params
    -----------------------------------
    data : dictionary
        dictionary containing string : list of numbers for k, v pairs
    max_avg : int
        mean of each list should be <= to max_avg
    min_range : int
        range of each list should be <= to min_range

    Returns
    -----------------------------------
    list
        list of all keys that satisfy all requirements

    >>> data = {"Marina": [100, 92, 114, 175, 138], \
        "Elvy": [32, 86, 91, 10, 23, 66], \
        "Yuru": [14.41, 12.21, 10.01, 13, 11.1], \
        "Madeline": [84.82, 91.96, 31.32], \
        "Sean": [66, 88, 45.6789, 88], \
        "Colin": [44.1214, 55.5663, 77], \
        "James": [10, 20, 30, 80, 30]}
    >>> advanced_search(data, 20, 2)
    ['Yuru']
    >>> advanced_search(data, 60, 5)
    ['Elvy', 'Colin']
    >>> advanced_search(data, 150, 50)
    ['Marina', 'Elvy', 'Madeline']
    >>> advanced_search(data, 80, 35)
    ['Elvy', 'Madeline']
    >>> advanced_search(data, 75.5, 12)
    ['Elvy', 'Madeline', 'Colin']
    >>> advanced_search(data, 12.5, 30)
    []
    """
    return list(map(lambda x: x[0], list(filter(
        lambda x: ((sum(x[1]) / len(x[1])) <= max_avg) & (
                (max(x[1]) - min(x[1])) >= min_range) & (
                          len(set(x[1])) == len(x[1])), data.items()))))


# Question 4
def best_curve_function(scores, funcs):
    """
    This function takes a list of raw scores (sampled from student submissions),
    and a list of curve functions submitted by tutors and returns the best curve
    function itself. If this function disqualifies all provided curve functions,
    it will return an identity function (a function that returns the argument
    itself) to indicate that no curve functions will be applied.

    Params
    -----------------------------------
    scores : list
        list of raw scores
    funcs : list
        list of functions

    Returns
    -----------------------------------
    function
        returns best curve function

    >>> best1 = best_curve_function([80.0, 90.0, 100.0], \
        [lambda score: score + 4.55, lambda score: score * 1.05, 105.0])
    >>> best1(100.0)
    104.55
    >>> best2 = best_curve_function([80.0, 90.0, 100.0], \
        [lambda score: score + 100, lambda score: score * 0.95, 103.5])
    >>> best2(95.5)
    95.5
    >>> best3 = best_curve_function([80.0, 90.0, 100.0], \
        [100.0, 103.5, False])
    >>> best3(91.0)
    91.0
    >>> best4 = best_curve_function(['', 'fsdfsdf', 100.0], \
        [100.0, 103.5, False])
    Traceback (most recent call last):
    AssertionError
    >>> best5 = best_curve_function([80.0, 90.0, 100.0], \
        [lambda score: score + 34, lambda score: score * 0.75, \
        lambda score: score - 12])
    >>> best5(95.0)
    95.0
    >>> best6 = best_curve_function([80.0, 90.0, 100.0], \
        [lambda score: score * 1.02, lambda score: (score * 1.01), \
        lambda score: score * 1.05])
    >>> best6(76.0)
    79.8
    """
    assert (all(list(
        map(lambda x: (isinstance(x, int) or (isinstance(x, float))), scores))))
    max_pts = 5
    funcs = list(filter(lambda x: x is not None, list(filter(lambda x: x if all(
        list(map(lambda new, old: (new >= old) & ((new - old) <= max_pts),
                 list(map(x, scores)), scores))) else None, list(
        filter(lambda x: callable(x), funcs))))))

    if len(funcs) == 0:
        return lambda x: x

    return max(list(
        map(lambda x: (x, sum(list(map(x, scores))) - sum(scores)), funcs)),
        key=lambda k: k[1])[0]


# Question 5 (Extra Credit)
def unique_data_generator(path):
    """
    Generator that traverses through a file, processes each line with the rule
    “key1,value1,value2,value3” -> ( key1, (value1, value2, value3) ),
    and yields the processed tuple.
    Only yields the first appearance of each key.

    Params
    -----------------------------------
    path : string
        file path

    Returns
    -----------------------------------
    generator
        returns a generator that iterates through the file

    >>> gen1 = unique_data_generator("infiles/data1.txt")
    >>> [next(gen1, None) for _ in range(3)]
    [('key1', ('val1', 'val2', 'val3')), ('key2', ('val1', 'val2')), \
('key3', ('val1', 'val2', 'val3', 'val4'))]
    >>> [next(gen1, None) for _ in range(5)]
    [('key4', ('val4', 'val5', 'val6')), \
('key5', ('val3', 'val4', 'val5', 'val6', 'val7')), None, None, None]
    >>> gen2 = unique_data_generator("infiles/data2.txt")
    >>> [next(gen2, None) for _ in range(5)]
    [('Colin', ('02-08-2021', '120')), ('James', ('02-08-2021', '100')), \
('Yuri', ('02-09-2021', '115')), ('Michelle', ('02-09-2021', '120')), \
('Sean', ('02-10-2021', '150'))]
    >>> gen3 = unique_data_generator("infiles/data3.txt")
    >>> [next(gen3, None) for _ in range(5)]
    [('Justin Wright-Foreman', ('43.6', '23.7%', '0.90')), ('Rayjon Tucker', \
('83.6', '18.5%', '0.68')), ('Mike Conley', ('192.1', '23.2%', '0.98')), \
('Nigel Williams-Goss', ('22.2', '19.9%', '0.78')), ('Georges Niang', \
('151.2', '17.5%', '0.71'))]
    >>> gen4 = unique_data_generator("infiles/data4.txt")
    >>> [next(gen4, None) for _ in range(4)]
    [('P.J. Dozier', ('171.2', '22.0%', '1.00')), ('Tyler Cook', \
('19.2', '10.9%', '0.63')), ('Noah Vonleh', ('13.6', '10.8%', '0.57')), \
('Jerami Grant', ('219.2', '19.0%', '0.75'))]
    >>> [next(gen4, None) for _ in range(6)]
    [('Paul Millsap', ('156.6', '18.2%', '0.84')), ('Monte Morris', \
('244.4', '18.9%', '0.77')), ('Bol Bol', ('81.8', '20.3%', '1.02')), \
('Nikola Jokic', ('225.8', '26.4%', '1.33')), ('Mason Plumlee', \
('164.4', '16.5%', '0.97')), ('Michael Porter Jr.', ('241.7', '23.4%', \
'1.12'))]
    """
    with open(path) as f:
        lines = f.readlines()
        keys = []
        for line in lines:
            line = line.replace('\n', '').split(',')
            key = line.pop(0)
            if key in keys:
                continue
            keys.append(key)
            values = tuple(line)
            yield (key, values)
