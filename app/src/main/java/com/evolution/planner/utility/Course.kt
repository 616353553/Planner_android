package com.evolution.planner.utility
import java.time.LocalDate
import java.time.format.DateTimeFormatter;
import java.util.*

class Course {

    companion object {
        val dateFormat = "yyyy-MM-dd'T'HH:mm:ssZ"
        val formatter = DateTimeFormatter.ofPattern(dateFormat, Locale.ENGLISH)
    }

    private var id: String? = null
    private var name: String
    private var owner: String
    private var semester: String
    private var description: String
    private var createDate: LocalDate
    private var taskIDs: ArrayList<String>? = null
//    private var tasks: ArrayList<Task>? = null

    constructor(id: String, data: Map<String, Any>) {
        this.id = id
        this.name = data["name"] as String
        this.owner = data["owner"] as String
        this.semester = data["semester"] as String
        this.description = data["description"] as String
        this.createDate = LocalDate.parse(data["create_date"] as String, formatter)
    }

    constructor(name: String, owner: String, semester: String, description: String, createDate: LocalDate) {
        this.name = name
        this.owner = owner
        this.semester = semester
        this.description = description
        this.createDate = createDate
    }

    public fun getID(): String? {
        return id
    }

    public fun getName(): String {
        return name
    }

    public fun getOwner(): String {
        return owner
    }

    public fun getSemester(): String {
        return semester
    }

    public fun getDescription(): String {
        return description
    }

    public fun getCreateDate(): LocalDate {
        return createDate
    }

    public fun getCreateDateString(format: String = "MM-dd-yyyy"): String {
        val formatter = DateTimeFormatter.ofPattern(format, Locale.ENGLISH)
        return this.createDate.format(formatter)
    }

    public fun toJSON(): Map<String, Any> {
        val data: MutableMap<String, Any> = mutableMapOf()
        data["name"] = this.name
        data["owner"] = this.owner
        data["semester"] = this.semester
        data["description"] = this.description
        data["create_date"] = this.createDate.format(formatter)
        return data
    }
}
