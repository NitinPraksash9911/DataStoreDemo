package com.example.datastoredemo

import androidx.datastore.CorruptionException
import androidx.datastore.DataStore
import androidx.datastore.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object TasksSerializer : Serializer<TaskStore> {
    override fun readFrom(input: InputStream): TaskStore {
        try {
            return TaskStore.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override fun writeTo(t: TaskStore, output: OutputStream) = t.writeTo(output)


}