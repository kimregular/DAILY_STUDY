from django.contrib import admin
from django.contrib.auth.admin import UserAdmin
from .models import User


@admin.register(User)
class CustomUserAdmin(UserAdmin):
    # fieldsets = None # 기본 필드셋을 사용하지 않도록 설정
    # fields = ('email', 'password', 'name',)

    fieldsets = (
        (
            "Profile",
            {
                "fields": (
                    "username",
                    "password",
                    "name",
                    "email",
                    "is_host",
                    "avatar",
                    "gender",
                    "language",
                    "currency",
                ),
                "classes": ("wide",),
            },
        ),
        (
            "Permissions",
            {
                "fields": (
                    "is_active",
                    "is_staff",
                    "is_superuser",
                    "groups",
                    "user_permissions",
                ),
            },
        ),
        (
            "Important dates",
            {"fields": ("last_login", "date_joined")},
        ),
    )

    list_display = (
        "username",
        "email",
        "name",
        "is_host",
    )
