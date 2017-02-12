import unittest
from prime_factor import PrimeFactor


class PrimeFactorTest(unittest.TestCase):
    def test_one(self):
        self.assertEqual([], PrimeFactor.generate(1))

    def test_two(self):
        self.assertEqual([2], PrimeFactor.generate(2))

    def test_three(self):
        self.assertEqual([3], PrimeFactor.generate(3))

    def test_four(self):
        self.assertEqual([2, 2], PrimeFactor.generate(4))

    def test_six(self):
        self.assertEqual([2, 3], PrimeFactor.generate(6))

    def test_eight(self):
        self.assertEqual([2, 2, 2], PrimeFactor.generate(8))

    def test_six(self):
        self.assertEqual([3, 3], PrimeFactor.generate(9))

if __name__ == '__main__':
    unittest.main()
