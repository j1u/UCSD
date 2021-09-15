"""
DSC 20 Homework 07
"""


def doctests_go_here():
    """
    All doctests should be written here.

    >>> available_time1 = [[1, 3, 21], [4, 6, 30], [9, 12, 8]]
    >>> ps1 = PersonalSchedule("Sun", "Curly", available_time1)
    >>> print(ps1)
    Curly Sun is available at [[1, 3, 21], [4, 6, 30], [9, 12, 8]].

    >>> available_time2 = [[1, 7, 20], [9, 10, 5], [11, 16, 8]]
    >>> ps2 = PersonalSchedule("Naes", "Sean", available_time2)
    >>> print(ps2)
    Sean Naes is available at [[1, 7, 20], [9, 10, 5], [11, 16, 8]].

    >>> t1 = Task(2, 5, 20, "Chopping potatoes")
    >>> t2 = Task(4, 5, 32, "Coding")
    >>> t3 = Task(11, 12, 8, "Playing League")
    >>> t4 = Task(1, 2, 21, "Gardening")
    >>> t5 = Task(11, 16, 6, "Jogging")
    >>> t6 = Task(4, 6, 20, "Chopping potatoes")
    >>> t7 = Task(6, 9, 20, "Chopping potatoes")

    >>> print(t1)
    Task Chopping potatoes starts at 2, ends at 5, requires 20 focus level.
    >>> print(t2)
    Task Coding starts at 4, ends at 5, requires 32 focus level.
    >>> print(t3)
    Task Playing League starts at 11, ends at 12, requires 8 focus level.

    >>> ps1.can_handle(t1)
    False
    >>> ps1.can_handle(t2)
    False
    >>> ps1.can_handle(t3)
    True
    >>> ps2.can_handle(t4)
    False
    >>> ps2.can_handle(t5)
    True

    >>> ps1.can_handle_task_sequence([t1, t2, t3])
    False
    >>> ps1.can_handle_task_sequence([t3, t4])
    True
    >>> ps2.can_handle_task_sequence([t3, t4])
    False
    >>> ps2.can_handle_task_sequence([t3, t5])
    True

    >>> t1.can_merge_task(t6)
    True
    >>> t1.can_merge_task(t7)
    False

    >>> merged_task1 = t1.merge_two_tasks(t6)
    >>> print(merged_task1)
    Task Chopping potatoes starts at 2, ends at 6, requires 20 focus level.

    >>> merged_task2 = t1.merge_two_tasks(t7)
    >>> print(merged_task2)
    None

    >>> ps2.handle_two_tasks(t1, t6)
    True
    >>> ps2.handle_two_tasks(t1, t7)
    False
    >>> ps2.handle_two_tasks(t6, t7)
    False

    >>> available_time3 = [[1, 7, 32], [8, 12, 30], [13, 14, 8]]
    >>> ps3 = PersonalSchedule("Jokic", "Nikola", available_time3)
    >>> print(ps3)
    Nikola Jokic is available at [[1, 7, 32], [8, 12, 30], [13, 14, 8]].

    >>> t8 = Task(13, 14, 3, "Triple Double")
    >>> print(t8)
    Task Triple Double starts at 13, ends at 14, requires 3 focus level.

    >>> available_time4 = [[1, 7, 14], [8, 12, 30], [13, 14, 24]]
    >>> ps4 = PersonalSchedule("Murray", "Jamal", available_time4)
    >>> print(ps4)
    Jamal Murray is available at [[1, 7, 14], [8, 12, 30], [13, 14, 24]].

    >>> t9 = Task(8, 11, 25, "50 ball")
    >>> print(t9)
    Task 50 ball starts at 8, ends at 11, requires 25 focus level.

    >>> available_time5 = [[1, 3, 6], [6, 18, 30], [18, 20, 9]]
    >>> ps5 = PersonalSchedule("Beal", "Bradley", available_time5)
    >>> print(ps5)
    Bradley Beal is available at [[1, 3, 6], [6, 18, 30], [18, 20, 9]].

    >>> t10 = Task(9, 14, 25, "50 ball")
    >>> print(t10)
    Task 50 ball starts at 9, ends at 14, requires 25 focus level.

    >>> t8.can_merge_task(t9)
    False
    >>> t8.can_merge_task(t4)
    False
    >>> t9.can_merge_task(t10)
    True

    >>> merged_task3 = t8.merge_two_tasks(t6)
    >>> print(merged_task3)
    None

    >>> merged_task4 = t10.merge_two_tasks(t9)
    >>> print(merged_task4)
    Task 50 ball starts at 8, ends at 14, requires 25 focus level.

    >>> merged_task5 = t9.merge_two_tasks(t6)
    >>> print(merged_task5)
    None

    >>> ps4.can_handle(t1)
    False
    >>> ps3.can_handle(t8)
    True
    >>> ps5.can_handle(t10)
    True

    >>> ps3.can_handle_task_sequence([t8, t9, t10])
    False
    >>> ps3.can_handle_task_sequence([t6, t8])
    True
    >>> ps4.can_handle_task_sequence([t8, t9])
    True

    >>> t8.can_merge_task(t9)
    False
    >>> t9.can_merge_task(t10)
    True

    >>> merged_task6 = t8.merge_two_tasks(t9)
    >>> print(merged_task6)
    None

    >>> merged_task7 = t9.merge_two_tasks(t10)
    >>> print(merged_task7)
    Task 50 ball starts at 8, ends at 14, requires 25 focus level.

    >>> ps4.handle_two_tasks(t8, t9)
    False
    >>> ps3.handle_two_tasks(t9, t10)
    False
    >>> ps5.handle_two_tasks(t8, t10)
    False
    """
    return


class Task:
    """
    Implementation of a task.
    """

    def __init__(self, start_time, end_time, focus_level_required, \
                 task_description):
        """
        Constructor of Task.
        Parameters:
        start_time (int): Start time of this task.
        end_time (int): End time of this task.
        focus_level_required (int): Focus level required for one person to
                        handle this task.
        task_description (str): Description of this task.
        """
        self.start_time = start_time
        self.end_time = end_time
        self.focus_level_required = focus_level_required
        self.task_description = task_description

    def get_start_time(self):
        """Getter for start_time attribute"""
        return self.start_time

    def get_end_time(self):
        """Getter for end_time attribute"""
        return self.end_time

    def get_focus_level_required(self):
        """Getter for focus_level_required attribute"""
        return self.focus_level_required

    def __str__(self):
        """
        String representation of Task.
        DO NOT CHANGE.
        """
        return ("Task {} starts at {}, ends at {}, requires {} focus level." \
                ).format(self.task_description, \
                         self.get_start_time(), self.get_end_time(), \
                         self.get_focus_level_required())

    def can_merge_task(self, other_task):
        """
        Give another Task called other_task, this function determines whether
        we are able to merge the current task and other_task.
        Requirement:
        Input validation
        Parameters
        other_task (Task): The other task to be merged with this task.
        Returns:
        True if we are able to merge those two tasks, False otherwise.
        """
        assert (isinstance(other_task, Task))
        return (self.focus_level_required == \
                other_task.get_focus_level_required()) & \
               (self.task_description == other_task.task_description) & \
               (any(i in range(self.start_time, self.end_time + 1) for i in \
                    range(other_task.get_start_time(),
                          other_task.get_end_time() + 1)))

    def merge_two_tasks(self, other_task):
        """
        Merge two tasks if the merge is possible.
        Requirement:
        Input validation
        Parameters:
        other_task (Task): The other task to be merged with this task.
        Returns:
        A new Task object after merging two tasks; otherwise, None is returned.
        """
        assert (isinstance(other_task, Task))
        if self.can_merge_task(other_task):
            return Task(min(self.start_time, other_task.get_start_time()), \
                        max(self.end_time, other_task.get_end_time()), \
                        self.focus_level_required, self.task_description)
        else:
            return


class PersonalSchedule:
    """
    Implementation of a personal schedule.
    """

    def __init__(self, last_name, first_name, available_time):
        """
        Constructor of PersonalSchedule.
        Parameters:
        last_name (str): Last name of this person.
        first_name (str): First name of this person.
        available_time (list[list]): A list of available intervals. Each
                        interval has three elements:
                        [start_time, end_time, focus_level]. You may assume
                        that given intervals don't have overlaps with one
                        another.
        """
        self.last_name = last_name
        self.first_name = first_name
        self.available_time = available_time

    def get_last_name(self):
        """Getter for last_name attribute"""
        return self.last_name

    def get_first_name(self):
        """Getter for first_name attribute"""
        return self.first_name

    def get_available_time(self):
        """Getter for available_time attribute"""
        return self.available_time

    def __str__(self):
        """
        String representation of PersonalSchedule.
        DO NOT CHANGE.
        """
        return "{} {} is available at {}.".format(self.get_first_name(), \
                                                  self.get_last_name(),
                                                  self.get_available_time())

    def can_handle(self, task):
        """
        This function determines whether this person can handle the given task.
        Requirement:
        Input validation
        Parameters:
        task (Task): A task that this person needs to handle.
        Returns:
        True if there exists a time interval in the schedule that can properly
        handle the task with the required focus level, False otherwise.
        """
        ps_focus_level = 2
        assert (isinstance(task, Task))
        return any([((i[0] <= task.get_start_time() <= i[1]) and (
                    i[0] <= task.get_end_time() <= i[1])) and (
                         i[ps_focus_level] >= task.get_focus_level_required())
                    for i in self.available_time])

    def can_handle_task_sequence(self, task_sequence):
        """
        Given a list of tasks, this function determines whether this person
        can handle this task sequence.
        Requirement:
        Input validation
        Parameters:
        task_sequence (list[Task]): A list of tasks this person needs to handle.
        Returns:
        True if all tasks can be properly handled, False otherwise. To simplify
        the question, we assume that multitasking is possible, i.e., a person
        can handle multiple tasks in a single time interval.
        """
        assert (all([isinstance(i, Task) for i in task_sequence]))
        return all([self.can_handle(t) for t in task_sequence])

    def handle_two_tasks(self, task1, task2):
        """
        Given two tasks, the function determines whether this personal can
        handle them together.
        Requirement:
        Input validation
        Parameters:
        task1 (Task): The first task.
        task2 (Task): The second task.
        Returns:
        True if two tasks can be merged and handled by this PersonalSchedule,
        False otherwise.
        """
        assert (isinstance(task1, Task) and isinstance(task2, Task))
        if task1.can_merge_task(task2):
            new_task = task1.merge_two_tasks(task2)
            return self.can_handle(new_task)
        else:
            return False

# END OF FILE #
