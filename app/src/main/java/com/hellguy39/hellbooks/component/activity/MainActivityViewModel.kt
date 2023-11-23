package com.hellguy39.hellbooks.component.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellguy39.hellbooks.domain.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject
constructor(
    private val bookRepository: BookRepository,
): ViewModel() {

    fun prepareData() {
        viewModelScope.launch {
            if (bookRepository.getBooks().isEmpty()) {
                bookRepository.insertFakeBooks()
            }
        }
    }
}