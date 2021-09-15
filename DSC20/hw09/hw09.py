"""
DSC 20 Homework 09
"""


# Question 1
def check_inputs(input1, input2):
    """
    Checks the validity of the inputs using the following criteria: input1
    should be a list, all of the values in input1 should be numeric
    (can be empty), input2 should be numeric, and input2 should be
    contained in input1

    Params
    ---------------------------------------
    input1 : list
        list numeric values
    input2 : int, float
        any numeric

    Raises
    ---------------------------------------
    TypeError : if input1 not list or all values in input1 are not numeric or
                input2 not numeric or input2 not in input1

    Return
    ---------------------------------------
    validated : string
        string stating that the input is valid

    >>> check_inputs([1, 2.0, 3.0, 4], 4)
    'Input validated'
    >>> check_inputs([], 1)
    Traceback (most recent call last):
    ...
    TypeError: input2 not in input1
    >>> check_inputs(1, 1)
    Traceback (most recent call last):
    ...
    TypeError: input1 is not the correct type
    >>> check_inputs([1, 2, 'hi'], 4)
    Traceback (most recent call last):
    ...
    TypeError: The element at index 2 is not numeric
    >>> check_inputs([1.0, 2.0, 3.0], 'hello')
    Traceback (most recent call last):
    ...
    TypeError: input2 is not the correct type

    >>> check_inputs([5, 'd', 7], 'g')
    Traceback (most recent call last):
    ...
    TypeError: The element at index 1 is not numeric
    >>> check_inputs([23.0, 65.0, 3.7], 3.7)
    'Input validated'
    >>> check_inputs(5, 7)
    Traceback (most recent call last):
    ...
    TypeError: input1 is not the correct type
    """
    if not isinstance(input1, list):
        raise TypeError('input1 is not the correct type')
    ls = [isinstance(i, float) or isinstance(i, int) for i in input1]
    idx = ['index ' + str(i) for i, j in enumerate(ls) if not j]
    if not all(ls):
        raise TypeError('The element at {} is not numeric'.format(
            ', '.join(idx)))
    if not (isinstance(input2, float) or isinstance(input2, int)):
        raise TypeError('input2 is not the correct type')
    if input2 not in input1:
        raise TypeError('input2 not in input1')
    return 'Input validated'


# Question 2
def load_file(filename):
    """
    Returns the number of words.txt in a file

    Params
    ---------------------------------------
    filename : string
        path to file

    Raises
    ---------------------------------------
    TypeError : if filename is not string
    ValueError : if file is empty
    FileNotFoundError : if file does not exist

    Return
    ---------------------------------------
    word_count : int
        number of words.txt in file

    >>> load_file(1)
    Traceback (most recent call last):
    ...
    TypeError: filename is not a string
    >>> load_file('files/ten_words.txt')
    10
    >>> load_file('files/empty.txt')
    Traceback (most recent call last):
    ...
    ValueError: File is empty
    >>> load_file('files/nonexistant.txt')
    Traceback (most recent call last):
    ...
    FileNotFoundError: files/nonexistant.txt does not exist

    >>> load_file('files/words.txt')
    19
    >>> load_file('files/more.txt')
    26
    >>> load_file(5)
    Traceback (most recent call last):
    ...
    TypeError: filename is not a string
    """
    if not isinstance(filename, str):
        raise TypeError('filename is not a string')
    try:
        with open(filename) as f:
            txt = f.read()
            if len(txt) < 1:
                raise ValueError('File is empty')
            else:
                tmp = [w for w in txt.replace('\n', ' ').split() if len(w) > 0]
                return len(tmp)
    except FileNotFoundError:
        raise FileNotFoundError('{} does not exist'.format(filename))


# Question 3
def q3_doctests():
    """
    Q3 doctests go here.

    >>> h = Nonmetal("H")
    >>> h
    Nonmetal("H")
    >>> print(h)
    Nonmetal name: H, atomic number: 8, period: 2, group: 2
    >>> h.get_mass()
    66

    >>> f = Metal("F")
    >>> f
    Metal("F")
    >>> print(f)
    Metal name: F, atomic number: 6, period: 1, group: 6
    >>> f.get_mass()
    78

    >>> f == h
    False
    >>> f != h
    True
    >>> f > h
    True
    >>> f < h
    False

    >>> water = Compound("H2O1")
    >>> water
    Compound("H2O1")
    >>> print(water)
    H2O1
    >>> water.elements
    {'H': 2, 'O': 1}
    >>> water.get_compound_mass()
    255

    >>> yummy_metal = Compound("U1")
    >>> dsc2 = Compound("D2S2C2")
    >>> dsc3 = Compound("D3S3C3")
    >>> cse = Compound("C7S8E9")
    >>> lava = Compound("H3O4")
    >>> obsidian = Compound("H5O5")
    >>> smelly_gas = Compound("H2")

    >>> water == yummy_metal
    True
    >>> water <= yummy_metal
    True
    >>> water > dsc2
    False
    
    >>> dsc2 + dsc3
    Compound("C5D5S5")
    >>> water - smelly_gas
    Compound("O1")
    >>> dsc2 + cse
    Traceback (most recent call last):
    ...
    ValueError
    >>> water - lava
    Traceback (most recent call last):
    ...
    ValueError
    >>> water + lava == obsidian
    True

    >>> metal = Metal("w")
    >>> print(metal)
    Metal name: W, atomic number: 23, period: 4, group: 5
    >>> metal
    Metal("W")
    >>> metal.get_mass()
    281
    >>> nonmetal = Nonmetal("o")
    >>> print(nonmetal)
    Nonmetal name: O, atomic number: 15, period: 3, group: 3
    >>> nonmetal
    Nonmetal("O")
    >>> nonmetal.get_mass()
    123
    >>> cpd = Compound("h7k9l7d6")
    >>> cpd2 = Compound("h5k6l4")
    >>> cpd3 = Compound("h5k6l4")
    >>> print(cpd)
    H7K9L7D6
    >>> cpd
    Compound("H7K9L7D6")
    >>> cpd.get_compound_mass()
    2943
    >>> metal == nonmetal
    False
    >>> metal != nonmetal
    True
    >>> metal > nonmetal
    True
    >>> metal >= nonmetal
    True
    >>> metal < nonmetal
    False
    >>> metal <= nonmetal
    False
    >>> cpd == cpd2
    False
    >>> cpd != cpd2
    True
    >>> cpd > cpd2
    True
    >>> cpd >= cpd2
    True
    >>> cpd < cpd2
    False
    >>> cpd <= cpd2
    False
    >>> cpd + cpd2
    Traceback (most recent call last):
    ...
    ValueError
    >>> cpd - cpd2
    Compound("H2K3L3")
    """
    return


LIST_METAL = "FKLPQRUVWXZ"


class Element:
    """
    Class that abstracts an element

    Object Attributes
    ---------------------------------------
    name : string
        name of element
    atomic_num : int
        atomic num of element
    period : int
        period of element
    group : int
        group of element

    Methods
    ---------------------------------------
    get_mass()
        Returns the mass of the element
    """

    def __init__(self, name):
        """
        Constructor of Element
        Input validation is required
        Parameter:
        name (str): a single uppercase character from 'A' to 'Z' that
                    represents the name of the element
        """
        if (len(name) != 1) and not (str.isalpha(name)):
            raise ValueError('invalid argument')
        ascii_conversion = 64
        group_length = 6
        self.name = name.upper()
        self.atomic_num = ord(self.name) - ascii_conversion
        self.period = (self.atomic_num // group_length) + (
                self.atomic_num % group_length > 0)
        self.group = self.atomic_num - (group_length * (self.period - 1))

    def get_mass(self):
        """
        Returns atomic mass of this element
        This method is a placeholder to avoid style check errors in some
        editors or tools. You will overwrite this method in the subclasses.
        """
        # DO NOT MODIFY #
        raise NotImplementedError("must be implemented in the subclasses")

    def __eq__(self, other_elem):
        """
        Returns True when two Elements are equal.
        Equality is determined by their atomic mass
        """
        return self.get_mass() == other_elem.get_mass()

    def __ne__(self, other_elem):
        """ Returns True when two Elements are not equal """
        return self.get_mass() != other_elem.get_mass()

    def __gt__(self, other_elem):
        """ Returns True when this Element is greater than the other """
        return self.get_mass() > other_elem.get_mass()

    def __ge__(self, other_elem):
        """
        Returns True when this Element is greater than or
        equal to the other
        """
        return self.get_mass() >= other_elem.get_mass()

    def __lt__(self, other_elem):
        """ Returns True when this Element is less than the other """
        return self.get_mass() < other_elem.get_mass()

    def __le__(self, other_elem):
        """
        Returns True when this Element is less than or
        equal to the other
        """
        return self.get_mass() <= other_elem.get_mass()

    def __repr__(self):
        """ Returns object representation of this Element """
        repr_form = "{0}(\"{1}\")"
        class_name = self.__class__.__name__
        repr_form = repr_form.format(class_name, self.name)
        return repr_form


class Nonmetal(Element):
    """
    Class that represents a nonmetal element

    Object Attributes
    ---------------------------------------
    name : string
        name of element
    atomic_num : int
        atomic num of element
    period : int
        period of element
    group : int
        group of element

    Methods
    ---------------------------------------
    get_mass()
        Returns the mass of the element
    """

    def get_mass(self):
        """ Returns atomic mass of this Nonmetal element """
        multiplier = 8
        return multiplier * self.atomic_num + self.period

    def __str__(self):
        """ Returns string representation of this Nonmetal element """
        # uncomment the following code
        str_form = "Nonmetal name: {}, atomic number: {}, period: {}, group: {}"
        return str_form.format(self.name, self.atomic_num, self.period,
                               self.group)


class Metal(Element):
    """
    Class that represents a metal element

    Object Attributes
    ---------------------------------------
    name : string
        name of element
    atomic_num : int
        atomic num of element
    period : int
        period of element
    group : int
        group of element

    Methods
    ---------------------------------------
    get_mass()
        Returns the mass of the element
    """

    def get_mass(self):
        """ Returns atomic mass of this Metal element """
        multiplier = 12
        return multiplier * self.atomic_num + self.group

    def __str__(self):
        """ Returns string representation of this Metal element """
        str_form = "Metal name: {}, atomic number: {}, period: {}, group: {}"
        return str_form.format(self.name, self.atomic_num, self.period,
                               self.group)


class Compound:
    """
    Class that represents a compound element

    Object Attributes
    ---------------------------------------
    name : string
        name of compound
    elements : dict
        dictionary containing all of the elements in the compound
    compound_mass : int
        total mass of all elements in the compound

    Methods
    ---------------------------------------
    get_compound_mass()
        Returns the total mass of all of the elements in the compound
    """

    def __init__(self, name):
        """
        Constructor of Compound
        Input validation is required
        Parameter:
        name (str): a string that represents the name of the compound
        """
        skip = 2
        if not (''.join(name[0::skip]).isalpha()) and not (
                ''.join(name[1::skip]).isnumeric()):
            raise ValueError('invalid argument')
        self.name = name.upper()
        self.elements = {name[i].upper(): int(name[i + 1]) for i in
                         range(0, len(name), skip)}
        self.compound_mass = sum([Metal(i[0]).get_mass() * i[1] if (
                i[0] in LIST_METAL) else Nonmetal(i[0]).get_mass() * i[1]
                                  for i in self.elements.items()])

    def get_compound_mass(self):
        """ A simple getter of compound_mass """
        return self.compound_mass

    def __eq__(self, other_comp):
        """
        Returns True when two Compounds are equal.
        Equality is determined by their compound mass
        """
        return self.compound_mass == other_comp.compound_mass

    def __ne__(self, other_comp):
        """ Returns True when two Compounds are not equal """
        return self.compound_mass != other_comp.compound_mass

    def __gt__(self, other_comp):
        """ Returns True when this Compound is greater than the other """
        return self.compound_mass > other_comp.compound_mass

    def __ge__(self, other_comp):
        """
        Returns True when this Compound is greater than or
        equal to the other
        """
        return self.compound_mass >= other_comp.compound_mass

    def __lt__(self, other_comp):
        """ Returns True when this Compound is less than the other """
        return self.compound_mass < other_comp.compound_mass

    def __le__(self, other_comp):
        """
        Returns True when this Compound is less than or
        equal to the other
        """
        return self.compound_mass <= other_comp.compound_mass

    def __add__(self, other_comp):
        """
        Synthesize a new Compound by adding this Compound with another
        Exception:
        ValueError will be raised if the product is invalid
        """
        new = []
        for i in self.elements.items():
            for j in other_comp.elements.items():
                if j[0] == i[0]:
                    new_num = i[1] + j[1]
                    if not (0 <= new_num <= 9):
                        raise ValueError
                    if new_num != 0:
                        new.append(j[0] + str(new_num))
        return Compound(''.join(sorted(new)))

    def __sub__(self, other_comp):
        """
        Decompose this Compound by subtracting another from it. A new product
        is returned after decomposition
        Exception:
        ValueError will be raised if the product is invalid
        """
        max = 9
        new = []
        for i in self.elements.items():
            for j in other_comp.elements.items():
                if j[0] == i[0]:
                    new_num = i[1] - j[1]
                    if not (0 <= new_num <= max):
                        raise ValueError
                    if new_num != 0:
                        new.append(j[0] + str(new_num))
                else:
                    if len(other_comp.elements.items()) <= 1:
                        new.append(i[0] + str(i[1]))
        return Compound(''.join(sorted(new)))

    def __str__(self):
        """ Returns string representation of this Compound """
        return self.name

    def __repr__(self):
        """ Returns object representation of this Compound """
        repr_form = "{0}(\"{1}\")"
        class_name = self.__class__.__name__
        return repr_form.format(class_name, self.name)
