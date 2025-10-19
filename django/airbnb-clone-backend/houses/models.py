from django.db import models


class House(models.Model):
    """Model Definition for House"""

    name = models.CharField(max_length=140)
    price = models.PositiveIntegerField()
    description = models.TextField()
    address = models.CharField(max_length=140)
    pets_allowed = models.BooleanField(default=True, help_text="If false, pets are not allowed.")

    def __str__(self):
        return self.name