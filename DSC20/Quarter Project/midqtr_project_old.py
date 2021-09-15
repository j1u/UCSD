"""
DSC 20 Mid-Quarter Project
Name: James Lu, Matin Ghaffari
"""


# Part 1: RGB Image #
class RGBImage:
    """
    TODO: add description
    """

    def __init__(self, pixels):
        """
        TODO: add description
        """
        self.pixels = pixels

    def size(self):
        """
        TODO: add description
        """
        return (len(self.pixels[0]), len(self.pixels[0][0]))

    def get_pixels(self):
        """
        TODO: add description
        """
        return [[[pix for pix in row] for row in channel] for channel in self.pixels]

    def copy(self):
        """
        TODO: add description
        """
        return RGBImage(self.get_pixels())

    def get_pixel(self, row, col):
        """
        TODO: add description
        """
        assert (isinstance(row, int) and isinstance(col, int) and
                (0 <= row <= self.size()[0]) and (0 <= col <= self.size()[1]))
        return (self.pixels[0][row][col], self.pixels[1][row][col],
                self.pixels[2][row][col])

    def set_pixel(self, row, col, new_color):
        """
        TODO: add description
        """
        assert (isinstance(row, int) and isinstance(col, int) and
                (0 <= row <= self.size()[0]) and (0 <= col <= self.size()[1]))
        for i in range(len(self.pixels)):
            self.pixels[i][row][col] = new_color[i]


# Part 2: Image Processing Methods #
class ImageProcessing:
    """
    TODO: add description
    """

    @staticmethod
    def negate(image):
        """
        TODO: add description
        """
        return RGBImage(
            [[[255 - k for k in j] for j in i] for i in image.get_pixels()])

    @staticmethod
    def grayscale(image):
        """
        TODO: add description
        """
        gs = image.copy()
        [[gs.set_pixel(i, j, ((sum(gs.get_pixel(i, j)) // 3,) * 3)) for j in
          range(image.size()[1])] for i in range(image.size()[0])]
        return gs

    @staticmethod
    def clear_channel(image, channel):
        """
        TODO: add description
        """
        copy = image.get_pixels()
        new = [[0 for j in i] for i in copy[channel]]
        copy[channel] = new
        return RGBImage(copy)

    @staticmethod
    def crop(image, tl_row, tl_col, target_size):
        """
        TODO: add description
        """
        copy = image.get_pixels()
        copy = [i[tl_row:] for i in copy]
        copy = [[j[tl_col:] for j in i] for i in copy]
        br_row, br_col = (tl_row + target_size[0]), (tl_col + target_size[1])
        if (br_row <= image.size()[0]) & (br_col <= image.size()[1]):
            copy = [i[:target_size[0]] for i in copy]
            copy = [[j[:target_size[1]] for j in i] for i in copy]
        return RGBImage(copy)

    @staticmethod
    def chroma_key(chroma_image, background_image, color):
        """
        TODO: add description
        """
        assert (isinstance(chroma_image, RGBImage) and isinstance(
            background_image, RGBImage) and (
                        chroma_image.size() == background_image.size()))
        ch_copy = chroma_image.copy()
        [[ch_copy.set_pixel(i, j, background_image.get_pixel(i, j)) for j
          in range(ch_copy.size()[1]) if
          (chroma_image.get_pixel(i, j) == color)] for i in
         range(ch_copy.size()[0])]
        return ch_copy

    # rotate_180 IS FOR EXTRA CREDIT (points undetermined)
    @staticmethod
    def rotate_180(image):
        """
        TODO: add description
        """
        copy = image.copy()
        copy = [i[::-1] for i in copy.get_pixels()]
        copy = [[j[::-1] for j in i] for i in copy]
        return RGBImage(copy)


# Part 3: Image KNN Classifier #
class ImageKNNClassifier:
    """
    TODO: add description
    """

    def __init__(self, n_neighbors):
        """
        TODO: add description
        """
        self.n_neighbors = n_neighbors
        self.data = []

    def fit(self, data):
        """
        TODO: add description
        """
        assert ((len(data) > self.n_neighbors) and (len(self.data) == 0)) 
        self.data = data

    @staticmethod
    def distance(image1, image2):
        """
        TODO: add description
        """
        assert image1.size() == image2.size()

        img1_copy = image1.copy().get_pixels()
        img2_copy = image2.copy().get_pixels()

        img1_flat = [pix for channel in img1_copy for row in channel for pix in row]
        img2_flat = [pix for channel in img2_copy for row in channel for pix in row]

        return (sum([(img1_flat[i] + img2_flat[i])**(2) for i in range(len(img1_flat))]))**(1/2)


        # inner = [for i in img1_copy for j in img1_copy[i] for k in img1_copy[i][j]]
        # print(inner)

    @staticmethod
    def vote(candidates):
        """
        TODO: add description
        """
        return max(set(candidates), key=candidates.count)

		

    def predict(self, image):
        """
        TODO: add description
        """
        assert len(self.data) != 0

        distances = [(self.distance(image, i[0]), i[1]) for i in self.data] 
        distances.sort(key=lambda x: x[0])
        neigbors = distances[:self.n_neighbors]
        #neigbors = [i for (v, i) in sorted((v, i) for (i, v) in enumerate(distances))][:self.n_neighbors]
        return self.vote([i[1] for i in neigbors])  

