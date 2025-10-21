from django.contrib import admin
from .models import Experience, Perk

@admin.register(Experience)
class ExperienceAdmin(admin.ModelAdmin):

    list_display = (
        "name",
        "price",
        "start",
        "end",
        "created_at",
        "updated_at",
    )

    list_filter = (
        "price",
        "start",
        "end",
        "perks",
        "created_at",
        "updated_at",
    )

@admin.register(Perk)
class PerkAdmin(admin.ModelAdmin):
    
    list_display = (
        "name",
        "details",
        "explanation",
        "created_at",
        "updated_at",
    )
    readonly_fields = (
        "created_at",
        "updated_at",
    )