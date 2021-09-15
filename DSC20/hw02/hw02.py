"""
DSC 20 Homework 02
"""

# Question 1
def convert_to_tuples(courses, instructors):
    """
    Takes list of courses and list of instructors and returns a list of tuples
    where the first element in the tuple is the course code and the second is 
    the instructor, returns empty list if no courses are provided.

    Params
    -----------------------------------
    courses : list
    	list of courses
    instructors : list
		list of instructors

	Returns
	-----------------------------------
	list
		list of tuples where for each tuple the first element is the course
		code, and the second is the instructor. Empty list if no courses are
		provided

    Assumptions:
        len(courses) >= len(instructors).

    >>> convert_to_tuples(['DSC10', 'DSC20', 'DSC30', 'DSC40B',
    ... 'DSC80', 'DSC180A'], ['Justin Eldridge', 'Marina Langlois',
    ... 'Marina Langlois', 'Justin Eldridge', 'Marina Langlois',
    ... 'Aaron Fraenkel'])
    [('DSC10', 'Justin Eldridge'), ('DSC20', 'Marina Langlois'), \
('DSC30', 'Marina Langlois'), ('DSC40B', 'Justin Eldridge'), \
('DSC80', 'Marina Langlois'), ('DSC180A', 'Aaron Fraenkel')]

    >>> convert_to_tuples(['DSC102', 'DSC106', 'DSC100'],
    ... ['Arun Kumar', 'Thomas Powell'])
    [('DSC102', 'Arun Kumar'), ('DSC106', 'Thomas Powell'), \
('DSC100', 'STAFF')]

    >>> convert_to_tuples(['DSC90', 'DSC190'], [])
    [('DSC90', 'STAFF'), ('DSC190', 'STAFF')]

    >>> convert_to_tuples(['DSC102', 'DSC106', 'DSC100'],
    ... ['Arun Kumar'])
    [('DSC102', 'Arun Kumar'), ('DSC106', 'STAFF'), \
('DSC100', 'STAFF')]

	>>> convert_to_tuples([], ['Arun Kumar', 'Thomas Powell'])
	[]

    >>> convert_to_tuples([], [])
    []
    """
    if len(courses) == 0:
    	return []
    else:
    	course_tuples = []
    	if len(instructors) < len(courses):
    		for i in range(len(courses) - len(instructors)):
    			instructors += ['STAFF']
    	for i in range(len(courses)):
    		course_tuples.append((courses[i], instructors[i]))
    	return course_tuples


# Question 2
def convert_to_dict(tuples):
    """
    Takes a list of tuples with each tuple of size 2 and creates a dictionary
    where the keys are the second element in the tuple and the values are the 
    first element in the tuple.

    Params
    -----------------------------------
    tuples : list
    	list of tuples with each tuple of size 2

	Returns
	-----------------------------------
	dictionary
		a dictionary containing all of the tuples converted into key, value
		pairs, returns empty dict if no tuples are provided

    Assumptions:
        there aren't duplicate instructor names or course codes.

    >>> convert_to_dict([('DSC10', 'Justin Eldridge'),
    ... ('DSC20', 'Marina Langlois'), ('DSC30', 'Marina Langlois'),
    ... ('DSC40B', 'Justin Eldridge'), ('DSC80', 'Marina Langlois'),
    ... ('DSC180A', 'Aaron Fraenkel')])
    {'Justin Eldridge': ['DSC10', 'DSC40B'], \
'Marina Langlois': ['DSC20', 'DSC30', 'DSC80'], 'Aaron Fraenkel': ['DSC180A']}

    >>> convert_to_dict([('DSC102', 'Arun Kumar'),
    ... ('DSC106', 'Thomas Powell'), ('DSC100', 'STAFF')])
    {'Arun Kumar': ['DSC102'], 'Thomas Powell': ['DSC106'], \
'STAFF': ['DSC100']}

    >>> convert_to_dict([('DSC90', 'STAFF'), ('DSC190', 'STAFF')])
    {'STAFF': ['DSC90', 'DSC190']}

    >>> convert_to_dict([('DSC102', 'Arun Kumar'),
    ... ('DSC106', 'Thomas Powell'), ('DSC100', 'STAFF'), ('DSC104', 'STAFF')])
    {'Arun Kumar': ['DSC102'], 'Thomas Powell': ['DSC106'], \
'STAFF': ['DSC100', 'DSC104']}

	>>> convert_to_dict([('DSC10', 'Justin Eldridge'),
	... ('DSC20', 'Aaron Fraenkel'), ('DSC30', 'Aaron Fraenkel'),
	... ('DSC40B', 'Justin Eldridge'), ('DSC80', 'Justin Eldridge'),
	... ('DSC180A', 'Aaron Fraenkel')])
	{'Justin Eldridge': ['DSC10', 'DSC40B', 'DSC80'], \
'Aaron Fraenkel': ['DSC20', 'DSC30', 'DSC180A']}

	>>> convert_to_dict([])
	{}
    """
    if len(tuples) == 0:
    	return {}
    else:
    	tuple_dict = {}
    	for i in tuples:
    		if i[1] in tuple_dict.keys():
    			temp = tuple_dict.get(i[1]) + [i[0]]
    			tuple_dict[i[1]] = temp
    		else:
    			tuple_dict[i[1]] = [i[0]]
    	return tuple_dict


# Question 3
def encrypt(string, k):
    """
    Takes a string and integer k and encrypts the string using the following
    formula: odd characters of string + reverse(even characters of string)

	Params
    -----------------------------------
    string : str
    	string to be encrypted
    k : int
		integer k representing the number of times the encryption method
		should be run

	Returns
	-----------------------------------
	string
		encrypted string

    Assumptions:
        k is a positive integer

    >>> encrypt("marina", 1)
    'aianrm'
    >>> encrypt("InfinityEdge", 2)
    'iyeEnIftgdin'
    >>> encrypt("James", 3)
    'msaJe'
    >>> encrypt("LeBron", 2)
    'roLBne'
    >>> encrypt("Luka", 5)
    'aLku'
    >>> encrypt("Jokic", 9)
    'cJiok'
    """
    encrypted = string
    for i in range(k):
    	even_chars = encrypted[0::2]
    	odd_chars = encrypted[1::2]
    	encrypted = odd_chars + even_chars[::-1]
    return encrypted


# Question 4
def context_words(document, target_word, window_size):
    """
    Generates a list of labels for the context of a given word.

    Params
    -----------------------------------
    document : str
    	string that is being analyzed
    target word : str
		the target word to be searched for
	window_size : int
		the number of words that should be found both in front of and behind
		the target word 

	Returns
	-----------------------------------
	list
		list of tuples in the form (target_word, context_word) for the target
		words that are passed

    Assumptions:
        `document` is always a string where words are seperated by spaces.
        `document` string will only have uppercase or lowercase letters,
            hyphens (only in compound words), and spaces.
        `target_word` will always exist in the `document`.


    >>> test_doc = "fifty-two UNITS from lower-division courses " + \
    "AND sixty UNITS from upper-division courses"
    >>> context_words(test_doc, "lower-division", 2)
    [('lower-division', 'units'), ('lower-division', 'from'), \
('lower-division', 'courses'), ('lower-division', 'and')]
    >>> context_words(test_doc, "upper-division", 2)
    [('upper-division', 'units'), ('upper-division', 'from'), \
('upper-division', 'courses')]
    >>> context_words(test_doc, "units", 1)
    [('units', 'fifty-two'), ('units', 'from'), ('units', 'sixty'), \
('units', 'from')]
	>>> context_words(test_doc, "from", 1)
	[('from', 'units'), ('from', 'lower-division'), ('from', 'units'), \
('from', 'upper-division')]
	>>> context_words(test_doc, "and", 2)
	[('and', 'lower-division'), ('and', 'courses'), ('and', 'sixty'), \
('and', 'units')]
	>>> context_words(test_doc, "courses", 2)
	[('courses', 'from'), ('courses', 'lower-division'), ('courses', 'and'), \
('courses', 'sixty'), ('courses', 'from'), ('courses', 'upper-division')]
    """
    doc = document.lower().split(' ')
    target_index = []
    
    j = 0
    for i in doc:
    	if i == target_word:
    		target_index += [j]
    	j += 1

    labels = []
    pos = list(range(1, window_size + 1))
    neg = []
    for i in pos:
    	neg += [-i]
    rg = neg[::-1] + pos
    for g in target_index:
    	for i in rg:
    		if ((g + i) >= 0) & ((g + i) < len(doc)):
    			labels += [(doc[g], doc[g + i])]
    return labels


# Question 5
def transpose_matrix(matrix_path):
    """
    Given a matrix in a file, transpose the matrix and write the new matrix
    to a new file

    Params
    -----------------------------------
    matrix_path : str
    	string of the path to file

    Assumptions:
        The matrix will be represented as lines of space-seperated integers.
        The matrix will be N x M, where N and M >= 1.

    >>> transpose_matrix("testfiles/matrix1.txt")
    >>> with open("testfiles/matrix1_transposed.txt", "r") as output1:
    ...     print(output1.read().strip())
    1 0 1 0 0
    0 1 0 1 0
    1 0 1 0 1
    0 1 0 1 0
    0 0 1 0 1
    >>> transpose_matrix("testfiles/matrix2.txt")
    >>> with open("testfiles/matrix2_transposed.txt", "r") as output2:
    ...     print(output2.read().strip())
    1 2 3
    2 3 4
    3 4 5
    4 5 6
    5 6 7
    >>> transpose_matrix("testfiles/matrix3.txt")
    >>> with open("testfiles/matrix3_transposed.txt", "r") as output3:
    ...     print(output3.read().strip())
    105 52 88 90 42 99
    12 51 1 770 31 10051
    90 96 2 1 17 81
    >>> transpose_matrix("testfiles/matrix4.txt")
    >>> with open("testfiles/matrix4_transposed.txt", "r") as output4:
    ...     print(output4.read().strip())
    39 34 234 23 23
    345 76 54 754 64
    534 23 23 23 23
    234 76 65 7561 74
    >>> transpose_matrix("testfiles/matrix5.txt")
    >>> with open("testfiles/matrix5_transposed.txt", "r") as output5:
    ...     print(output5.read().strip())
    99 35 345 90 87 786 87 35 90
    345 1 12 345 51 31 51 1 345
    81 378 642 1 96 17 96 378 1
    >>> transpose_matrix("testfiles/matrix6.txt")
    >>> with open("testfiles/matrix6_transposed.txt", "r") as output6:
    ...     print(output6.read().strip())
    99 345 35 90
    345 12 1 345
    81 642 378 1
    23 8776 34 76
    87 565 87 34
    5 878 5 90
    6 76 7 23
    545 34 9 67
    """
    with open(matrix_path) as f:
    	text = f.readlines()
    	matrix = ''
    	for i in range(len(text[0].split(' '))):
    	    for j in text:
    	   		j = j.replace('\n', '').split(' ')
    	   		g = j[i]
    	   		matrix += g + ' '
    	    matrix = matrix[:-1] + '\n'
    	new_path = matrix_path.split('.')
    	with open(new_path[0] + '_transposed.' + new_path[1], 'w') as wr:
    		wr.write(matrix)
    return


# Question 6.1
def parse_timelog(timelog_path):
    """
    Reads time log at provided path, parses all entries into a dictionary where
    the keys are names and the values are lists containing tuples in the form
    (date, minutes)

    Params
    -----------------------------------
    timelog_path : str
    	string of the path to timelog file

	Returns
	-----------------------------------
	dictionary
		where the keys are names and the values are lists containing tuples 
		in the form (date, minutes)

    Assumptions:
        The log will be sorted in chronological order.
        Each line of the time log will have name (str), date (str,
            MM-DD-YYYY), and minutes seperated by comma (",").
            For example: "Marina,09-30-2021,300".
        Each person will have only one entry per day.

    >>> parse_timelog("testfiles/timelog1.txt")
    {'Marina': [('09-30-2021', 300), ('10-01-2021', 300)], \
'Huy': [('09-30-2021', 120), ('10-01-2021', 215)], \
'Siddharth': [('09-30-2021', 185), ('10-01-2021', 90)]}
    >>> parse_timelog("testfiles/timelog2.txt")
    {'Colin': [('10-08-2021', 120), ('10-09-2021', 10), ('10-10-2021', 90), \
('10-11-2021', 30)], 'James': [('10-08-2021', 100), ('10-09-2021', 85)], \
'Shubham': [('10-09-2021', 115)], 'Jianming': [('10-09-2021', 120)], \
'Sean': [('10-10-2021', 150)]}
    >>> parse_timelog("testfiles/timelog3.txt")
    {'Fred': [('10-08-2021', 23)], 'James': [('10-08-2021', 65)], \
'Shrek': [('10-09-2021', 45)], 'Shubham': [('10-09-2021', 654)], \
'Richard': [('10-09-2021', 542), ('10-11-2021', 30)], \
'PinPon': [('10-09-2021', 433)], 'Colin': [('10-10-2021', 543)], \
'Donkey': [('10-10-2021', 543)]}
    >>> parse_timelog("testfiles/timelog4.txt")
    {'Shrek': [('10-09-2021', 624)], 'Bron': [('10-09-2021', 654), \
('10-11-2021', 30), ('10-09-2021', 542)], \
'James': [('10-08-2021', 665)], 'Fred': [('10-08-2021', 23)], \
'PinPon': [('10-09-2021', 54)], 'Colin': [('10-10-2021', 345)], \
'Luka': [('10-10-2021', 56)]}
    >>> parse_timelog("testfiles/timelog5.txt")
    {'PinPon': [('10-08-2021', 23), ('10-10-2021', 234), \
('10-09-2021', 235)], 'Trae': [('10-09-2021', 45)], \
'Donkey': [('10-10-2021', 63)], 'James': [('10-08-2021', 752)], \
'Richard': [('10-11-2021', 543)], 'Jokic': [('10-09-2021', 676)], \
'Shubham': [('10-09-2021', 543)]}
    """
    with open(timelog_path) as f:
    	text = f.readlines()
    	dictionary = {}
    	for i in text:
    		j = i.replace('\n', '').split(',')
    		if j[0] in dictionary.keys():
    			temp = dictionary.get(j[0]) + [(j[1], int(j[2]))]
    			dictionary[j[0]] = temp
    		else:
    			dictionary[j[0]] = [(j[1], int(j[2]))]
    	return dictionary


# Question 6.2
def extract_extreme_time(data, is_max):
    """
    Takes dictionary and finds max or min minutes worked in each value list
    and returns a dictionary where the keys are names, and the values are
    min / max entry

    Params
    -----------------------------------
    data : dict
    	dictionary to search through
    is_max : boolean
    	true - finds max, false - finds min

	Returns
	-----------------------------------
	dictionary
		where the keys are names and the values are the max / min entry

    Assumptions:
        When any comparison results a tie, keep the entry with earlier date.

    >>> data1 = parse_timelog("testfiles/timelog1.txt")
    >>> extract_extreme_time(data1, True)
    {'Marina': ('09-30-2021', 300), 'Huy': ('10-01-2021', 215), \
'Siddharth': ('09-30-2021', 185)}
    >>> extract_extreme_time(data1, False)
    {'Marina': ('09-30-2021', 300), 'Huy': ('09-30-2021', 120), \
'Siddharth': ('10-01-2021', 90)}
    >>> data2 = parse_timelog("testfiles/timelog2.txt")
    >>> extract_extreme_time(data2, True)
    {'Colin': ('10-08-2021', 120), 'James': ('10-08-2021', 100), \
'Shubham': ('10-09-2021', 115), 'Jianming': ('10-09-2021', 120), \
'Sean': ('10-10-2021', 150)}
    >>> extract_extreme_time(data2, False)
    {'Colin': ('10-09-2021', 10), 'James': ('10-09-2021', 85), \
'Shubham': ('10-09-2021', 115), 'Jianming': ('10-09-2021', 120), \
'Sean': ('10-10-2021', 150)}
    >>> data3 = parse_timelog("testfiles/timelog3.txt")
    >>> extract_extreme_time(data3, True)
    {'Fred': ('10-08-2021', 23), 'James': ('10-08-2021', 65), \
'Shrek': ('10-09-2021', 45), 'Shubham': ('10-09-2021', 654), \
'Richard': ('10-09-2021', 542), 'PinPon': ('10-09-2021', 433), \
'Colin': ('10-10-2021', 543), 'Donkey': ('10-10-2021', 543)}
    >>> data4 = parse_timelog("testfiles/timelog4.txt")
    >>> extract_extreme_time(data4, False)
    {'Shrek': ('10-09-2021', 624), 'Bron': ('10-11-2021', 30), \
'James': ('10-08-2021', 665), 'Fred': ('10-08-2021', 23), \
'PinPon': ('10-09-2021', 54), 'Colin': ('10-10-2021', 345), \
'Luka': ('10-10-2021', 56)}
    >>> data5 = parse_timelog("testfiles/timelog5.txt")
    >>> extract_extreme_time(data5, True)
    {'PinPon': ('10-09-2021', 235), 'Trae': ('10-09-2021', 45), \
'Donkey': ('10-10-2021', 63), 'James': ('10-08-2021', 752), \
'Richard': ('10-11-2021', 543), 'Jokic': ('10-09-2021', 676), \
'Shubham': ('10-09-2021', 543)}
    """
    max_min_dict = {}
    for i in data.keys():
        vals = data.get(i)
        max_min = vals[0][1]
        idx = 0
        for j in range(len(vals)):
            if is_max:
                if vals[j][1] > max_min:
                    max_min = vals[j][1]
                    idx = j
                elif (vals[j][1] == max_min):
                    #both dates in form yr, day, month
                    date = vals[j][0].split('-')[::-1]
                    other_date = vals[0][0].split('-')[::-1]
                    if date[0] < other_date[0]:
                        max_min = vals[j][1]
                        idx = j
                    elif (date[0] == other_date[0]) & \
                    (date[2] < other_date[2]):
                        max_min = vals[j][1]
                        idx = j
                    elif (date[0] == other_date[0]) & \
                    (date[2] == other_date[2]) & (date[1] < other_date[1]):
                        max_min = vals[j][1]
                        idx = j
            else:
                if vals[j][1] < max_min:
                    max_min = vals[j][1]
                    idx = j
                elif vals[j][1] == max_min:
                    date = vals[j][0].split('-')[::-1]
                    other_date = vals[0][0].split('-')[::-1]
                    if date[0] < other_date[0]:
                        max_min = vals[j][1]
                        idx = j
                    elif (date[0] == other_date[0]) & \
                    (date[2] < other_date[2]):
                        max_min = vals[j][1]
                        idx = j
                    elif (date[0] == other_date[0]) & \
                    (date[2] == other_date[2]) & (date[1] < other_date[1]):
                        max_min = vals[j][1]
                        idx = j
        max_min_dict[i] = vals[idx]
    return max_min_dict
