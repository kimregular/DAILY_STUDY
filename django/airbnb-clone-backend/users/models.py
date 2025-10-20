from django.db import models
from django.contrib.auth.models import AbstractUser


class User(AbstractUser):
    class GenderChoices(models.TextChoices):
        MALE = ("male", "Male")  # value, label
        FEMALE = ("female", "Female")
        # value -> DB에 저장되는 값
        # label -> admin 페이지 등에서 보여지는 값

    class LanguageChoices(models.TextChoices):
        ENGLISH = ("en", "English")
        KOREAN = ("kr", "Korean")
        CHINESE = ("cn", "Chinese")
        JAPANESE = ("jp", "Japanese")

    class CurrencyChoices(models.TextChoices):
        USD = ("usd", "USD")
        KRW = ("krw", "KRW")
        CNY = ("cny", "CNY")
        JPY = ("jpy", "JPY")

    first_name = models.CharField(
        max_length=150,
        editable=False,
    )
    # AbstractUser의 first_name, last_name 필드를 사용하지 않기 위해 editable=False 설정
    last_name = models.CharField(
        max_length=150,
        editable=False,
    )

    name = models.CharField(
        max_length=150,
        default="",
    )
    avatar = models.ImageField(
        blank=True,
    )
    is_host = models.BooleanField(default=False)
    gender = models.CharField(
        max_length=10,
        choices=GenderChoices.choices,
    )
    language = models.CharField(
        max_length=2,
        choices=LanguageChoices.choices,
    )
    currency = models.CharField(
        max_length=3,
        choices=CurrencyChoices.choices,
    )

    class Meta:
        db_table = "users"
