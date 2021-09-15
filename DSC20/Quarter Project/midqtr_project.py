"""
DSC 20 Mid-Quarter Project
Name: James Lu, Matin Ghaffari
"""


# Part 1: RGB Image #
class RGBImage:
    """
    A class used to represent an image

    Attributes
    ---------------------------------------
    pixels : list
        a 3D list to represent each pixel in an image

    Methods
    ---------------------------------------
    size()
        returns # of rows / cols in the image
    get_pixels()
        returns a deep copy of the images pixels attribute
    copy()
        returns a deep copy of this RGBImage object
    get_pixel(row, col)
        returns the RGB value of a pixel in this object
    set_pixel(row, col, new_color)
        sets a pixel in this object to the new RGB value
    """

    def __init__(self, pixels):
        """
        This constructor initializes a RGBImage instance and initializes an
        instance variable for the pixels, which is an 3-dimensional
        argument, where ​pixels[c][i][j] indicates the intensity value in
        channel ​c ​at position (​i​, ​j​).

        Params
        ---------------------------------------
        pixels : list
            3D list containing values of each pixel in each color channel
        """
        self.pixels = pixels

    def size(self):
        """
        This is a getter method that returns the size of the image, where
        size is defined as a tuple of (number of rows, number of columns).

        Return
        ---------------------------------------
        size : tuple
            tuple containing the size of the image, # of rows and # of columns
        """
        return (len(self.pixels[0]), len(self.pixels[0][0]))

    def get_pixels(self):
        """
        This is a getter method returns a ​deep copy of the pixels
        matrix of the image (as a 3-dimensional list). This method creates
        this returned deep copy via list comprehension.

        Return
        ---------------------------------------
        pixels : list
            deep copy of the 3D list self.pixels
        """
        return [[[value for value in row] for row in channel]
                for channel in self.pixels]

    def copy(self):
        """
        This method first calls get_pixels() to attain a deep copy of the
        pixels matrix. Then this method returns a copy of the ​RGBImage
        instance by creating a new RGBImage ​instance by using the deep copy
        of the ​pixels ​matrix. Then this new RGBImage instance is then
        returned.

        Return
        ---------------------------------------
        copy : RGBImage
            deep copy of this RGB object
        """
        return RGBImage(self.get_pixels())

    def get_pixel(self, row, col):
        """
        This getter method first asserts that the arguments for row and column
        are valid indices then this getter method returns the color of the
        pixel and its position (​row,​ ​col​). The color is returned as a
        3-element tuple: (red intensity, green intensity, blue intensity)
        at its corresponding position.

        Params
        ---------------------------------------
        row : int
            integer value representing the desired row of the image
        col : int
            integer value representing the desired column of the image

        Return
        ---------------------------------------
        pixel : tuple
            3 element tuple containing the value in each color channel
        """
        RED_CHANNEL = 0
        GREEN_CHANNEL = 1
        BLUE_CHANNEL = 2

        assert (isinstance(row, int) and isinstance(col, int) and
                (0 <= row < self.size()[0]) and (0 <= col < self.size()[1]))

        return (self.pixels[RED_CHANNEL][row][col],
                self.pixels[GREEN_CHANNEL][row][col],
                self.pixels[BLUE_CHANNEL][row][col])

    def set_pixel(self, row, col, new_color):
        """
        This setter method first asserts that the arguments for row and column
        are valid indices. Furthermore, this method has an argument for
        new_color, which ​is a 3-element tuple (red intensity, green intensity,
        blue intensity). Then this method updates the color of the pixel at
        position (​row​, ​col​) to the new_color ​in place, except in the case that
        any of the color intensities in this tuple is provided as -1,
        which in this case the method wouldn't update the intensity at the
        corresponding channel.

        Params
        ---------------------------------------
        row : int
            integer value representing the desired row of the image
        col : int
            integer value representing the desired column of the image
        new_color : tuple
            3 element tuple representing the new values to be set at each
            color channel
        """
        assert (isinstance(row, int) and isinstance(col, int) and
                (0 <= row < self.size()[0]) and (0 <= col < self.size()[1]))

        for idx in range(len(self.pixels)):
            if new_color[idx] != -1:
                self.pixels[idx][row][col] = new_color[idx]


# Part 2: Image Processing Methods #
class ImageProcessing:
    """
    A class used to process an image

    Methods
    ---------------------------------------
    negate(image)
        returns a new negated RBGImage from the given RGBImage object
    greyscale(image)
        returns a new grayscaled RGBImage from the given RGBImage object
    clear_channel(image, channel)
        returns a new RGBImage with a specified empty channel from the given
        RGBImage object
    crop(image, tl_row, tl_col, target_size)
        returns a new cropped RGBImage from the given RGBImage object
    chroma_key(chroma_image, background_image, color)
        returns a new chroma-keyed RGBImage from the two given RGBImage
        objects
    rotate_180(image)
        returns a new RGBImage rotated 180 degrees from a given RBGImage
        object
    """

    @staticmethod
    def negate(image):
        """
        This method creates a negative image, with all pixel values of the
        argument, image, inverted. This method performs this by accesing each
        pixel with current intensity value ​val​, and updates it with
        (255 - ​val)​ via list comprehension. Then this method returns the
        negative image of the given ​image.​

        Params
        ---------------------------------------
        image : RGBImage
            image to be negated

        Return
        ---------------------------------------
        negated : RGBImage
            new negated RBGImage
        """
        MAX_COLOR_CODE = 255

        return RGBImage(
            [[[MAX_COLOR_CODE - value for value in row] for row in channel]
             for channel in image.get_pixels()])

    @staticmethod
    def grayscale(image):
        """
        This method converts the given image to grayscale. For each pixel
        (R, G, B) in the pixels matrix this method calculates the average
        (R + G + B) / 3 and updates all channels with this average

        Params
        ---------------------------------------
        image : RGBImage
            image to be grayscaled

        Return
        ---------------------------------------
        negated : RGBImage
            new grayscaled RBGImage
        """
        gs = image.copy()
        DIMENSIONS = 3

        [[gs.set_pixel(row, col, (
                    (sum(gs.get_pixel(row, col)) // DIMENSIONS,) * DIMENSIONS))
          for col in range(image.size()[1])] for row in range(image.size()[0])]

        return gs

    @staticmethod
    def clear_channel(image, channel):
        """
        This method clears a given channel of the image. To do this, every
        value in the specified channel of the image is set to 0.

        Params
        ---------------------------------------
        image : RGBImage
            image to be manipulated
        channel : int
            channel to be cleared [0 - 2]

        Return
        ---------------------------------------
        channel_cleared : RGBImage
            new RBGImage with the given channel cleared
        """
        copy = image.get_pixels()
        new_channel = [[0 for value in row] for row in copy[channel]]
        copy[channel] = new_channel
        return RGBImage(copy)

    @staticmethod
    def crop(image, tl_row, tl_col, target_size):
        """
        A method that crops an image. Arguments tl_row and tl_col specify the
        position of the top-left corner of the cropped image, target_size
        specifies the size of the image after cropping. It is a tuple in the
        form of (number of rows, number of columns), when the specified
        target_size is too large, the actual size of the cropped image will
        be smaller.

        Params
        ---------------------------------------
        image : RGBImage
            image to be manipulated
        tl_row : int
            integer representing the top left row of cropped image
        tl_col : int
            integer representing the top left column of cropped image
        target_size : tuple
            target size of new cropped image

        Return
        ---------------------------------------
        cropped : RGBImage
            new cropped RBGImage
        """
        copy = image.get_pixels()
        copy = [channel[tl_row:] for channel in copy]
        copy = [[row[tl_col:] for row in channel] for channel in copy]

        br_row, br_col = (tl_row + target_size[0]), (tl_col + target_size[1])

        if (br_row <= image.size()[0]) & (br_col <= image.size()[1]):
            copy = [channel[:target_size[0]] for channel in copy]
            copy = [[row[:target_size[1]] for row in channel] for channel in
                    copy]

        return RGBImage(copy)

    @staticmethod
    def chroma_key(chroma_image, background_image, color):
        """
        This method performs the chroma key algorithm on a chroma_image by
        replacing all pixels with the specified color in the chroma_image to
        the pixels at the same places in the background_image. If the color
        is not present in the chroma_image, no pixel will be replaced but a
        new copy will still be returned.

        Params
        ---------------------------------------
        chroma_image : RGBImage
            image to be chroma-keyed
        background_image : RGBImage
            image that is used to chroma-key pixels
        color : tuple
            3 element tuple representing the color that is replaced in
            chroma_image

        Return
        ---------------------------------------
        chroma-keyed : RGBImage
            new chroma-keyed RBGImage
        """
        assert (isinstance(chroma_image, RGBImage)
                and isinstance(background_image, RGBImage)
                and (chroma_image.size() == background_image.size()))

        chroma_copy = chroma_image.copy()

        [[chroma_copy.set_pixel(row, col,
                                background_image.get_pixel(row, col))
          for col in range(chroma_copy.size()[1])
          if (chroma_image.get_pixel(row, col) == color)]
         for row in range(chroma_copy.size()[0])]

        return chroma_copy

    # rotate_180 IS FOR EXTRA CREDIT (points undetermined)
    @staticmethod
    def rotate_180(image):
        """
        This method rotates an image 180 degrees by reversing the values
        in each row and column.

        Params
        ---------------------------------------
        image : RGBImage
            image to be rotated

        Return
        ---------------------------------------
        rotated : RGBImage
            new rotated RBGImage
        """
        copy = image.copy()
        copy = [channel[::-1] for channel in copy.get_pixels()]
        copy = [[row[::-1] for row in channel] for channel in copy]

        return RGBImage(copy)


# Part 3: Image KNN Classifier #
class ImageKNNClassifier:
    """
    This class uses KNN classification and predicts the label of an image by
    finding the most popular labels in a collection (with size k) of nearest
    training data.

    Attributes
    ---------------------------------------
    n_neighbors : int
        integer representing the size of the neighborhood

    Methods
    ---------------------------------------
    fit(data)
        fit the classifier by storing all training data in the classifier
        instance.
    distance(image1, image2)
        returns the Euclidean distance between RGB image image1 and image2.
    vote(candidates)
        returns the most popular label from a list of candidates labels.
        If there is a tie when determining the majority label any of them are
        returned.
    predict(image)
        returns a prediction of the label of a given image using the
        KNN classification algorithm.
    """

    def __init__(self, n_neighbors):
        """
        Initializes a ImageKNNClassifier instance with the argument
        n_neighbors. n_neighbors defines the size of the nearest neighborhood
        and when predicting the labels, the classifier will look for the
        majority between the n_neighbors closest images.

        Params
        ---------------------------------------
        n_neighbors : int
            integer representing the size of the neighborhood
        """
        self.n_neighbors = n_neighbors
        self.data = None

    def fit(self, data):
        """
        Fits the classifier by storing all training data in the
        classifier instance.

        Params
        ---------------------------------------
        data : list
            list of (image, label) tuples, where image is an RGBImage
            instance and label is a string.
        """
        assert ((len(data) > self.n_neighbors) and self.data is None)

        self.data = data

    @staticmethod
    def distance(image1, image2):
        """
        This method calculates the Euclidean distance between 2 RBGImage
        instances. To calculate the total Euclidean distance we sum the
        squared difference and square root the entire sum.

        Params
        ---------------------------------------
        image1 : RGBImage
            first image
        image2 : RGBImage
            second image

        Return
        ---------------------------------------
        distance : float
            total euclidean distance between the two images
        """
        assert (isinstance(image1, RGBImage) and isinstance(image2, RGBImage))
        assert (image1.size() == image2.size())

        img1_flat = [value for channel in image1.get_pixels()
                     for row in channel for value in row]

        img2_flat = [value for channel in image2.get_pixels()
                     for row in channel for value in row]

        return (sum([(img1_flat[i] - img2_flat[i]) ** 2
                     for i in range(len(img1_flat))])) ** (1 / 2)

    @staticmethod
    def vote(candidates):
        """
        This method finds the most popular label from a list of candidates
        labels. If there is a tie when determining the majority label,
        any of them are returned.

        Params
        ---------------------------------------
        vote : list
            list of nearest neighbors

        Return
        ---------------------------------------
        most_popular : string
            most popular label from the list of candidates
        """
        return max(candidates, key=candidates.count)

    def predict(self, image):
        """
        This method predicts the label of the given image using the KNN
        classification algorithm.

        Params
        ---------------------------------------
        image : RGBImage
            image to be labelled

        Return
        ---------------------------------------
        prediction : string
            predicted label of the image
        """
        assert self.data is not None

        neighbors = sorted([(self.distance(image, data[0]), data[1])
                            for data in self.data],
                           key=lambda x: x[0])[:self.n_neighbors]

        return self.vote([neighbor[1] for neighbor in neighbors])
