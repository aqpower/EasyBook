<script setup lang="ts">
import { TransitionRoot, TransitionChild, Dialog, DialogPanel, DialogTitle } from '@headlessui/vue'
import { computed } from 'vue'

// 声明 props
const props = defineProps({
  visible: Boolean,
  title: String,
  content: String,
  btnContent: String,
  bigDialogEnable: {
    type: Boolean,
    default: false
  },
  close: {
    type: Function,
    default: (fun: () => any) => fun()
  }
})

const emits = defineEmits<{
  (event: 'update:visible', visible: boolean): void
  (event: 'close'): void
}>()

const dialogVisible = computed<boolean>({
  get() {
    return props.visible
  },
  set(visible) {
    emits('update:visible', visible)
    if (!visible) {
      emits('close')
    }
  }
})
</script>

<template>
  <TransitionRoot appear :show="visible" as="template" class="z-[1000]">
    <Dialog as="div" @close="dialogVisible = false" class="relative z-10">
      <TransitionChild
        as="template"
        enter="duration-300 ease-out"
        enter-from="opacity-0"
        enter-to="opacity-100"
        leave="duration-200 ease-in"
        leave-from="opacity-100"
        leave-to="opacity-0"
      >
        <div class="fixed inset-0 bg-base-content opacity-25" />
      </TransitionChild>

      <div class="fixed inset-0 overflow-y-auto">
        <div class="flex min-h-full min-w-full items-center justify-center p-4 text-center">
          <TransitionChild
            as="template"
            enter="duration-300 ease-out"
            enter-from="opacity-0 scale-95"
            enter-to="opacity-100 scale-100"
            leave="duration-200 ease-in"
            leave-from="opacity-100 scale-100"
            leave-to="opacity-0 scale-95"
          >
            <DialogPanel
              class="transform overflow-scroll rounded-2xl bg-base-100 text-left align-middle shadow-xl transition-all"
              :class="{ 'w-full mx-20 p-4': bigDialogEnable, 'w-96 p-6': !bigDialogEnable }"
              :style="{
                height: bigDialogEnable ? '92vh' : ''
              }"
            >
              <!-- style qudiao -->
              <DialogTitle v-show="!props.bigDialogEnable" class="text-xl font-medium leading-9 text-base-content">
                {{ props.title }}
              </DialogTitle>
              <div class="w-full h-full">
                <slot name="content" />
                <p class="text-base-content">
                  {{ props.content }}
                </p>
              </div>

              <div v-show="props.btnContent && props.bigDialogEnable == false" class="mt-4 flex justify-end">
                <button
                  type="button"
                  class="btn btn-primary inline-flex rounded-md border border-transparent px-4 py-2 text-sm font-medium focus:outline-none focus-visible:ring-2 focus-visible:ring-offset-2"
                  @click="dialogVisible = false"
                >
                  {{ props.btnContent }}
                  <slot name="btn-content" />
                </button>
              </div>
            </DialogPanel>
          </TransitionChild>
        </div>
      </div>
    </Dialog>
  </TransitionRoot>
</template>
